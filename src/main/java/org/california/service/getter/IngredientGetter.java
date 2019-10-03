package org.california.service.getter;

import org.california.model.entity.item.Ingredient;
import org.california.repository.item.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientGetter extends BaseNamedGetter<Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Autowired
    IngredientGetter(IngredientRepository ingredientRepository) {
        super(ingredientRepository, Ingredient.class);
        this.ingredientRepository = ingredientRepository;
    }


}
