package org.california.model.validator;

public class PlaceFormValidator extends Validator {

    boolean validateName(String name) {
        return validateSingleString(name, "name", 5, 60);
    }

}
