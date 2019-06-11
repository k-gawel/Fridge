package org.california.model.validator.ContainerFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.ContainerFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validatePlaceId {

    ContainerFormValidator validator;


    @Test
    public void id_is_null() {

        validator = new ContainerFormValidator();
        Long id = null;
        assertFalse(validator.validatePlaceId(id));
        assertTrue(validator.getMessages().contains("place_id.empty"));

    }


    @Test
    public void id_is_0() {

        validator = new ContainerFormValidator();
        Long id = 0L;
        assertFalse(validator.validatePlaceId(id));
        assertTrue(validator.getMessages().contains("place_id.empty"));

    }


    @Test
    public void proper_id() {

        validator = new ContainerFormValidator();
        Long id = 1L;
        assertTrue(validator.validatePlaceId(id));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }

}