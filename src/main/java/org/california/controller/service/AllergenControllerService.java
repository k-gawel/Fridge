package org.california.controller.service;

import org.california.model.entity.item.Allergen;
import org.california.model.transfer.response.NamedEntityDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.BaseNamedGetter;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergenControllerService extends BaseNamedControllerService<Allergen> {


    @Autowired
    public AllergenControllerService(GetterService getter, EntityToDtoMapper mapper, AccountPermissionsService permissionsService) {
        super(getter, mapper, permissionsService);
    }


    public NamedEntityDto get(String name) {
        Allergen allergen = getter.allergens.getByName(name).orElse(null);
        return mapper.toDto(allergen);
    }


    @Override
    protected BaseNamedGetter<Allergen> getter() {
        return getter.allergens;
    }


}
