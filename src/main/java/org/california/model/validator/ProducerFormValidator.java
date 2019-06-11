package org.california.model.validator;

public class ProducerFormValidator extends Validator {

    public boolean validateName(String name) {
        return validateSingleString(name, "producer_name", 3, 30);
    }

}
