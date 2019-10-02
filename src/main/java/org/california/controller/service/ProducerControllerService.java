package org.california.controller.service;

import org.california.model.entity.item.Producer;
import org.california.model.transfer.response.item.ProducerDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ProducerControllerService {

    private GetterService getter;
    private EntityToDtoMapper mapper;

    @Autowired
    public ProducerControllerService(GetterService getter, EntityToDtoMapper mapper) {
        this.getter = getter;
        this.mapper = mapper;
    }


    public Collection<ProducerDto> search(String idsString, String name, String nameStart) {
        Collection<Number> ids = StringUtils.collectionOf(idsString);

        Collection<Producer> resultList;

        if(!ids.isEmpty())
            resultList = getter.producers.getByKeys(ids);
        else if(org.apache.commons.lang3.StringUtils.isNotBlank(name))
            resultList = getter.producers.searchByName(name);
        else if(!org.apache.commons.lang3.StringUtils.isNotBlank(nameStart))
            resultList = getter.producers.getWhereNameStartsWith(nameStart);
        else
            return Collections.emptySet();

        return resultList.stream().map(mapper::toDto).collect(Collectors.toList());
    }


    public ProducerDto get(String name) {
        return mapper.toDto(getter.producers.getByName(name).orElse(null));
    }

}
