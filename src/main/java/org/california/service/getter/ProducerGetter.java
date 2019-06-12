package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Producent;
import org.california.repository.item.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Service
public class ProducerGetter {

    private final ProducerRepository producerRepository;

    @Autowired
    ProducerGetter(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }



    public Producent getByKey(Serializable key) {
        return producerRepository.getByKey(key);
    }


    public Collection<Producent> getByIds(Collection<Long> ids) {
        return CollectionUtils.isEmpty(ids) ?
                Collections.emptyList() : producerRepository.getByKeys(ids);
    }


    public Producent getByName(String name) {
        return producerRepository.getByName(name);
    }


    public Collection<Producent> searchByName(String name) {
        return producerRepository.searchByName(name);
    }


    public Collection<Producent> getWhereNameStartsWith(String nameStart) {
        return producerRepository.getWhereNameStartsWith(nameStart);
    }



}
