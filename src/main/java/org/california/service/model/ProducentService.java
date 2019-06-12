package org.california.service.model;

import org.california.model.entity.Producent;
import org.california.repository.item.ProducerRepository;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducentService {


    private ProducerRepository producerRepository;

    @Autowired
    public ProducentService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }


    public Producent getProducent(String name) {
        if(name == null)
            throw new NotValidException("producentname.null");

        Producent result = producerRepository.getByName(name);

        if(result != null)
            return result;

        result = new Producent();
        result.setName(name);
        return producerRepository.save(result);
    }

}
