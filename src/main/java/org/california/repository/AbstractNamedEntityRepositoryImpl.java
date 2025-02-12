package org.california.repository;

import org.california.model.entity.BaseEntity;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractNamedEntityRepositoryImpl<T extends BaseEntity> extends AbstractRepositoryImpl<T> implements AbstractNamedEntityRepository<T> {

    @Override
    public Optional<T> getByName(String name) {
        if (name == null) return Optional.empty();

        String HQL = "SELECT E FROM " + clazz.getName() + " E WHERE E.name = :name";

        Query<T> query = getSession().createQuery(HQL);
        query.setParameter("name", name);

        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    @Override
    public Collection<T> searchByName(String name) {
        if(name == null)
            return null;

        String HQL = "SELECT E FROM " + clazz.getName() + " E WHERE E.name LIKE :name";

        Query<T> query = getSession().createQuery(HQL);
        query.setParameter("name", "%" + name + "%");

        return query.getResultList();
    }


    @Override
    public Collection<T> getWhereNameStartsWith(String nameStart) {
        if(nameStart == null)
            return null;

        String HQL = "SELECT E FROM " + clazz.getName() + " E WHERE E.name LIKE :name";
        Query<T> query = getSession().createQuery(HQL);
        query.setParameter("name", nameStart + "%");

        return query.getResultList();
    }

}
