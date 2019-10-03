package org.california.controller.service;

import org.california.model.entity.item.Ingredient;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.BaseNamedGetter;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientControllerService extends BaseNamedControllerService<Ingredient> {


    @Autowired
    public IngredientControllerService(GetterService getter, EntityToDtoMapper mapper, AccountPermissionsService permissions) {
        super(getter, mapper, permissions);
    }


    @Override
    protected BaseNamedGetter<Ingredient> getter() {
        return getter.ingredients;
    }


}
