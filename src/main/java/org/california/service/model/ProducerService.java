package org.california.service.model;

import org.california.model.entity.item.Producer;
import org.california.repository.item.ProducerRepository;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {


    private ProducerRepository producerRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }


    public Producer gerProducer(String name) {
        if (name == null)
            throw new NotValidException("producername.null");

        return producerRepository.getByName(name)
                .orElseGet(() -> producerRepository.save(new Producer(name)));

    }

}
