package org.california.repository;

import java.io.Serializable;
import java.util.Collection;

public interface AbstractRepository<T> {

    T save(T entity);
    T getByKey(Serializable key);
    Collection<T> getByKeys(Collection<Long> ids);
    boolean delete(T entity);
    void setUUID();
}
