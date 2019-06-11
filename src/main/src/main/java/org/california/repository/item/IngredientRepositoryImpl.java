package org.california.repository.item;

import org.california.model.entity.Ingredient;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Ingredient> implements IngredientRepository {

    public IngredientRepositoryImpl() {
        setClazz(Ingredient.class);
    }

}
