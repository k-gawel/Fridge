package org.california.service.getter;

import org.california.model.entity.BaseEntity;
import org.california.repository.AbstractRepository;
import org.california.util.exceptions.NoContentException;

import java.util.Collection;
import java.util.Optional;

public abstract class BaseGetter<T extends BaseEntity> {

    protected final AbstractRepository<T> baseRepository;

    final Class<T> getterClass;

    BaseGetter(AbstractRepository<T> baseRepository, Class<T> getterClass) {
        this.getterClass = getterClass;
        this.baseRepository = baseRepository;
        GetterService.GETTER.put(getterClass, this);
    }

    public Optional<T> getByKey(Number key) {
        return Optional.ofNullable(baseRepository.getByKey(key));
    }

    public T getByKeyOrThrow(Number key) {
        return getByKey(key).orElseThrow(() -> new NoContentException(getterClass, key));
    }

    public Collection<T> getByKeys(Collection<? extends Number> keys) {
        return baseRepository.getByKeys(keys);
    }

}
