package org.california.model.validator;

public class ContainerFormValidator extends Validator {

    public boolean validateName(String name) {
        return validateSingleString(name, "name", 5, 20);
    }


    public boolean validatePlaceId(Long placeId) {

        if(placeId == null || placeId.equals(0L)) {
            setMessage("place_id.empty");
            return false;
        }

        return true;
    }

}
