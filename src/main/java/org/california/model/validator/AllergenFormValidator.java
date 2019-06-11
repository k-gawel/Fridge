package org.california.model.validator;

public class AllergenFormValidator extends Validator {

    public boolean validateName(String name) {
        return validateSingleString(name, "allergen", 3, 40);
    }

}
