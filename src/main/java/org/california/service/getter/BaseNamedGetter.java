package org.california.service.getter;

import org.california.model.entity.BaseNamedEntity;
import org.california.repository.AbstractNamedEntityRepository;

import java.util.Collection;
import java.util.Optional;

public abstract class BaseNamedGetter<T extends BaseNamedEntity> extends BaseGetter<T> {

    protected final AbstractNamedEntityRepository<T> baseRepository;
    final Class<T> getterClass;

    BaseNamedGetter(AbstractNamedEntityRepository<T> baseRepository, Class<T> getterClass) {
        super(baseRepository, getterClass);
        this.getterClass = getterClass;
        this.baseRepository = baseRepository;
    }


    public Optional<T> getByName(String name) {
        return baseRepository.getByName(name);
    }


    public Collection<T> searchByName(String name) {
        return baseRepository.searchByName(name);
    }


    public Collection<T> searchByStart(String nameStart) {
        return baseRepository.getWhereNameStartsWith(nameStart);
    }



}
