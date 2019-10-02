package org.california.controller.service;

import org.california.model.entity.item.Allergen;
import org.california.model.transfer.response.NamedEntityDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class AllergenControllerService extends BaseControllerService<Allergen> {


    @Autowired
    public AllergenControllerService(GetterService getter, EntityToDtoMapper mapper, AccountPermissionsService permissionsService) {
        super(getter, mapper, permissionsService);
    }


    public Collection<NamedEntityDto> search(String idsString, String name, String nameStart) {
        Collection<Number> ids = StringUtils.collectionOf(idsString);

        Collection<Allergen> resultList;

        if(!ids.isEmpty())
            resultList = getter.allergens.getByKeys(ids);
        else if(org.apache.commons.lang3.StringUtils.isNotBlank(name))
            resultList = getter.allergens.searchByName(name);
        else if(!org.apache.commons.lang3.StringUtils.isNotBlank(nameStart))
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
