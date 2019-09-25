package org.california.controller.service;

import org.apache.commons.lang3.StringUtils;
import org.california.controller.service.utils.Utils;
import org.california.model.entity.item.Allergen;
import org.california.model.transfer.response.NamedEntityDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class AllergenControllerService {


    private GetterService getter;
    private EntityToDtoMapper mapper;

    @Autowired
    public AllergenControllerService(GetterService getter, EntityToDtoMapper mapper) {
        this.getter = getter;
        this.mapper = mapper;
    }


    public Collection<NamedEntityDto> search(String idsString, String name, String nameStart) {
        Collection<Number> ids = Utils.collectionOf(idsString);

        Collection<Allergen> resultList;

        if(!ids.isEmpty())
            resultList = getter.allergens.getByKeys(ids);
        else if(StringUtils.isNotBlank(name))
            resultList = getter.allergens.searchByName(name);
        else if(!StringUtils.isNotBlank(nameStart))
            resultList = getter.allergens.getWhereNameStartsWith(nameStart);
        else
            return Collections.emptySet();

        return resultList.stream().map(mapper::toDto).collect(Collectors.toList());
    }


    public NamedEntityDto get(String name) {
        Allergen allergen = getter.allergens.getByName(name).orElse(null);
        return mapper.toDto(allergen);
    }

}
