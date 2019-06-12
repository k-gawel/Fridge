package org.california.controller.service;

import org.apache.commons.lang3.StringUtils;
import org.california.controller.service.utils.Utils;
import org.california.model.entity.Producent;
import org.california.model.transfer.response.EntityToDtoMapper;
import org.california.model.transfer.response.ProducerDto;
import org.california.service.getter.GetterService;
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
        Collection<Long> ids = Utils.collectionOf(idsString);

        Collection<Producent> resultList;

        if(!ids.isEmpty())
            resultList = getter.producers.getByIds(ids);
        else if(StringUtils.isNotBlank(name))
            resultList = getter.producers.searchByName(name);
        else if(!StringUtils.isNotBlank(nameStart))
            resultList = getter.producers.getWhereNameStartsWith(nameStart);
        else
            return Collections.emptySet();

        return resultList.stream().map(mapper::producerToDto).collect(Collectors.toList());
    }


    public ProducerDto get(String name) {
        return mapper.producerToDto(getter.producers.getByName(name));
    }

}
