package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.item.Producer;
import org.california.repository.item.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class ProducerGetter {

    private final ProducerRepository repository;

    @Autowired
    ProducerGetter(ProducerRepository producerRepository) {
        this.repository = producerRepository;
    }



    public Producent getByKey(Serializable key) {
        return producerRepository.getByKey(key);
    }


    public Collection<Producer> getByIds(Collection<Long> ids) {
        return CollectionUtils.isEmpty(ids) ?
                Collections.emptyList() : repository.getByKeys(ids);
    }


    public Optional<Producer> getByName(String name) {
        Producer result =  repository.getByName(name);
        return result != null ? Optional.of(result) : Optional.empty();
    }


    public Producer getByNameOrCreate(String name) {
        Producer currentProducer = getByName(name).orElse(null);
        if(currentProducer != null)
            return currentProducer;
        else {
            Producer ingredient = new Producer();
            ingredient.setName(name);
            return repository.save(ingredient);
        }
    }


    public Collection<Producer> searchByName(String name) {
        return repository.searchByName(name);
    }


    public Collection<Producer> getWhereNameStartsWith(String nameStart) {
        return repository.getWhereNameStartsWith(nameStart);
    }



}
