package org.california.service.getter;

import org.california.model.entity.item.Producer;
import org.california.repository.item.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProducerGetter extends BaseGetter<Producer> {

    private final ProducerRepository repository;

    @Autowired
    ProducerGetter(ProducerRepository producerRepository) {
        super(producerRepository, Producer.class);
        this.repository = producerRepository;
    }


    public Optional<Producer> getByName(String name) {
        return repository.getByName(name);
    }

    public Collection<Producer> searchByName(String name) {
        return repository.searchByName(name);
    }


    public Collection<Producer> getWhereNameStartsWith(String nameStart) {
        return repository.getWhereNameStartsWith(nameStart);
    }


    public Collection<Producer> getAll() {
        return repository.getAll();
    }
}
