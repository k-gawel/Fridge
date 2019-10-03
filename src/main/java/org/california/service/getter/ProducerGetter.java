package org.california.service.getter;

import org.california.model.entity.item.Producer;
import org.california.repository.item.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProducerGetter extends BaseNamedGetter<Producer> {

    private final ProducerRepository repository;

    @Autowired
    ProducerGetter(ProducerRepository producerRepository) {
        super(producerRepository, Producer.class);
        this.repository = producerRepository;
    }


    public Collection<Producer> getAll() {
        return repository.getAll();
    }


}