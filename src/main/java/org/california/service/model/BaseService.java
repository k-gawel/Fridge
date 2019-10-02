package org.california.service.model;

import org.california.repository.AbstractRepository;

public abstract class BaseService<T> {

    private final AbstractRepository<T> baseRepository;

    protected BaseService(AbstractRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    T save(T entity) {
        try {
            return baseRepository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
