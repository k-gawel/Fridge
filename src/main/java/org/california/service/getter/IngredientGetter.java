package org.california.service.getter;

import org.california.model.entity.item.Ingredient;
import org.california.repository.item.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class IngredientGetter extends BaseGetter<Ingredient> {

    private final IngredientRepository ingredientRepository;


    @Autowired
    IngredientGetter(IngredientRepository ingredientRepository) {
        super(ingredientRepository, Ingredient.class);
        this.ingredientRepository = ingredientRepository;
    }


    public Optional<Ingredient> getByName(String name) {
        return ingredientRepository.getByName(name);
    }


    public Collection<Ingredient> searchByName(String name) {
        return ingredientRepository.searchByName(name);
    }


    public Collection<Ingredient> getWhereNameStartsWith(String nameStart) {
        return ingredientRepository.getWhereNameStartsWith(nameStart);
    }

}
