package org.california.model.validator;

public class WishListFormValidator extends Validator {

    boolean validateName(String name) {
        return validateSingleString(name, "name", 5, 35);
    }

    boolean validateDescription(String description) {
        return validateSingleString(description, "description", 10, 600);
    }

    boolean validatePlace(long placeId) {
        return validateId(placeId, "place_id");
    }

}
