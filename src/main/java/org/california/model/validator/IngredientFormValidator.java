package org.california.model.validator;

public class IngredientFormValidator extends Validator {

    public boolean validateName(String name) {
        return validateSingleString(name, "ingredient", 4, 30);
    }

}
