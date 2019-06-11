package org.california.service.getter;

import org.california.model.entity.Ingredient;
import org.california.repository.item.IngredientRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

@Service
public class IngredientGetter {

    private IngredientRepository ingredientRepository;


    public IngredientGetter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    public Ingredient getByKey(Serializable key) {
        if(key == null)
            return null;

        return ingredientRepository.getByKey(key);
    }


    public Collection<Ingredient> getByIds(Collection<Long> ids) {
        return ingredientRepository.getByKeys(ids);
    }


    public Ingredient getByName(String name) {
        return ingredientRepository.getByName(name);
    }


    public Collection<Ingredient> searchByName(String name) {
        return ingredientRepository.searchByName(name);
    }


    public Collection<Ingredient> getWhereNameStartsWith(String nameStart) {
        return ingredientRepository.getWhereNameStartsWith(nameStart);
    }

}
