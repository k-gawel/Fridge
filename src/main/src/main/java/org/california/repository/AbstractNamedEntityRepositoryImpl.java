package org.california.repository;

import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractNamedEntityRepositoryImpl<T extends Serializable> extends AbstractRepositoryImpl<T> implements AbstractNamedEntityRepository<T> {

    @Override
    public T getByName(String name) {
        if(name == null)
            return null;

        String HQL = "SELECT E FROM " + clazz.getName() + " E WHERE E.name = :name";


        Query<T> query = getSession().createQuery(HQL);
        query.setParameter("name", name);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
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
