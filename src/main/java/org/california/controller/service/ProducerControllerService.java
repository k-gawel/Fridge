package org.california.controller.service;

import org.california.model.transfer.response.item.ProducerDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.BaseNamedGetter;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerControllerService extends BaseNamedControllerService {


    @Autowired
    public ProducerControllerService(GetterService getter, EntityToDtoMapper mapper, AccountPermissionsService permissions) {
        super(getter, mapper, permissions);
    }

    @Override
    protected BaseNamedGetter getter() {
        return getter.producers;
    }

    public ProducerDto get(String name) {
        return mapper.toDto(getter.producers.getByName(name).orElse(null));
    }

}
