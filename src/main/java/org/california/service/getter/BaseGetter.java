package org.california.service.getter;

import org.california.model.entity.BaseEntity;
import org.california.repository.AbstractRepository;
import org.california.repository.Repositories;
import org.california.util.exceptions.NoContentException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class BaseGetter<T extends BaseEntity> {

    protected final AbstractRepository<T> repository;

    final Class<T> getterClass;

    BaseGetter(AbstractRepository<T> repository, Class<T> getterClass) {
        this.getterClass = getterClass;
        this.repository = repository;
        GetterService.GETTER.put(getterClass, this);
    }

    public Optional<T> getByKey(Number key) {
        return Optional.ofNullable(repository.getByKey(key));
    }

    public T getByKeyOrThrow(Number key) {
        return getByKey(key).orElseThrow(() -> new NoContentException(getterClass, key));
    }

    public Collection<T> getByKeys(Collection<? extends Number> keys) {
        return repository.getByKeys(keys);
    }

}
