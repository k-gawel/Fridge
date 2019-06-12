package org.california.model.validator.ItemFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.ItemFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validatePlaceId {

    ItemFormValidator validator;


    @Test
    public void id_is_null() {

        validator = new ItemFormValidator();
        Long id = null;
        assertFalse(validator.validatePlaceId(id));
        assertTrue(validator.getMessages().contains("place_id.empty"));

    }

    @Test
    public void proper_id() {

        validator = new ItemFormValidator();
        Long id = 1L;
        assertTrue(validator.validatePlaceId(id));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }

}