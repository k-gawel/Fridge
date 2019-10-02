package org.california.repository;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;

@Transactional
public abstract class AbstractRepositoryImpl<T extends BaseEntity> implements AbstractRepository<T> {

    Class<T> clazz;

    @Autowired
    protected SessionFactory sessionFactory;

    protected final void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public T save(T entity) {
        try {
            getSession().saveOrUpdate(entity);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Transactional(readOnly = false)
    public boolean delete(T entity) {

        try {
            getSession().delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    @Transactional
    public void setUUID() {

        final String HQL = "SELECT E FROM " + clazz.getName() + " E";

        Query<T> query = getSession().createQuery(HQL);
        Collection<T> resultList = query.getResultList();
        resultList.forEach(e -> {
            BaseEntity entity = (BaseEntity) e;
            entity.setUuid(UUID.randomUUID().toString());
            getSession().persist(entity);
        });

    }


    @Transactional(readOnly = true)
    public T getByKey(Number key) {
        if(key == null)
            return null;

        return getSession().get(clazz, key);
    }

    @Override
    @Transactional(readOnly = false)
    public Collection<T> getByKeys(Collection<? extends Number> ids) {
        if(CollectionUtils.isEmpty(ids))
            return Collections.emptySet();

        String HQL = "SELECT E FROM " + clazz.getName() + " E WHERE id IN (:ids)";

        Query<T> query = getSession().createQuery(HQL);
        query.setParameterList("ids", ids);

        return query.getResultList();
    }


    protected String concatConditions(Collection<String> conditions) {

        StringBuilder resultBuilder = new StringBuilder();

        Iterator<String> iterator = conditions.iterator();
        String condition;

        while (iterator.hasNext()) {
            condition = iterator.next();
            resultBuilder.append(condition);
            if(iterator.hasNext()) resultBuilder.append(" AND ");
        }

        return resultBuilder.toString();

    }

}
