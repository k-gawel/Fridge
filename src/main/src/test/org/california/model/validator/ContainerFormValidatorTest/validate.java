package org.california.model.validator.ContainerFormValidatorTest;

import org.california.model.transfer.request.ContainerForm;
import org.california.model.validator.ContainerFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class validate {

    ContainerFormValidator validator;
    ContainerForm form;



    @Test
    public void nullable_values() {

        //  NULL PLACEID, NULL NAME
        validator = new ContainerFormValidator();
        form = new ContainerForm();
        form.setName(null);
        form.setPlaceId(null);
        assertFalse(validator.validate(form));

        // NULL PLACE ID, PROPER NAME
        validator = new ContainerFormValidator();
        form = new ContainerForm();
        form.setName("propername");
        form.setPlaceId(null);
        assertFalse(validator.validate(form));

        // PROPER PLACE ID, NULL NAME
        validator = new ContainerFormValidator();
        form = new ContainerForm();
        form.setName(null);
        form.setPlaceId(1L);
        assertFalse(validator.validate(form));

    }


    @Test
    public void not_valid_values() {

        // NOT_VALID PLACE, NOT_VALID NAME
        validator = new ContainerFormValidator();
        form = new ContainerForm();
        form.setName("12");
        form.setPlaceId(0L);
        assertFalse(validator.validate(form));

        // NOT_VALID PLACE ID, VALID NAME
        validator = new ContainerFormValidator();
        form = new ContainerForm();
        form.setName("propername");
        form.setPlaceId(0L);
        assertFalse(validator.validate(form));

        // VALID PLACE_ID, NOT VALID NAME
        validator = new ContainerFormValidator();
        form = new ContainerForm();
        form.setName("12");
        form.setPlaceId(1L);
        assertFalse(validator.validate(form));

    }


    @Test
    public void proper_values() {
        // PROPER PLACEID, PROPER NAME
        validator = new ContainerFormValidator();
        form = new ContainerForm();
        form.setName("propername");
        form.setPlaceId(1L);
        assertTrue(validator.validate(form));
    }


}