package org.california.model.validator;


import org.california.model.transfer.request.AllergenForm;
import org.california.model.transfer.request.IngredientForm;
import org.california.model.transfer.request.ProducerForm;

import java.util.Collection;

public class
ItemFormValidator extends Validator{


    public boolean validateName(String name) {
        return validateSingleString(name, "name", 10, 140);
    }


    public boolean validateCategoryId(Long categoryId) {
        return validateId(categoryId, "category_id");
    }


    public boolean validatePlaceId(Long placeId) {
        return validateId(placeId, "place_id");
    }


    public boolean validateDescription(String description) {
        if(description == null)
            return true;

        description = normalizeSpaces(description);
        if(description.trim().length() > 1500) {
            setMessage("description.too_long");
            return false;
        }

        return true;
    }


    public boolean validateStorage(String storage) {
        if(storage == null)
            return true;

        storage = normalizeSpaces(storage);
        if(storage.length() > 500) {
            setMessage("storage.too_long");
            return false;
        }

        return true;

    }


    public void validateAllergens(Collection<AllergenForm> allergenForms) {
        if(allergenForms == null)
            return;

        Validator allergenFormValidator = new AllergenFormValidator();

        allergenForms.removeIf(a -> !allergenFormValidator.validate(a));
    }


    public void validateIngredients(Collection<IngredientForm> ingredientForms) {
        if(ingredientForms == null)
            return;

        Validator ingredientFormValidator = new IngredientFormValidator();

        ingredientForms.removeIf(f -> !ingredientFormValidator.validate(f));
    }


    public boolean validateProducer(ProducerForm producerForm) {
        if(producerForm == null)
            return true;

        Validator producerFormValidator = new ProducerFormValidator();

        return producerFormValidator.validate(producerForm);
    }

}
