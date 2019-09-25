package org.california.repository;

import java.util.Collection;

public interface AbstractRepository<T> {

    T save(T entity);

    T getByKey(Number key);

    Collection<T> getByKeys(Collection<? extends Number> ids);
    boolean delete(T entity);
    void setUUID();

}
