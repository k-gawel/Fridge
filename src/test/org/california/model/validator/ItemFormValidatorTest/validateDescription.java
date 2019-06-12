package org.california.model.validator.ItemFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.ItemFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateDescription {

    ItemFormValidator validator;


    @Test
    public void too_long_description() {

        validator = new ItemFormValidator();
        String tooLongDescription = new String(new char[1501]).replaceAll("\0", "a");
        assertFalse(validator.validateDescription(tooLongDescription));
        assertTrue(validator.getMessages().contains("description.too_long"));

    }


    @Test
    public void blank_description() {

        validator = new ItemFormValidator();
        String nullDescription = null;
        assertTrue(validator.validateDescription(nullDescription));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

        validator = new ItemFormValidator();
        String empty = "";
        assertTrue(validator.validateDescription(empty));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }


    @Test
    public void not_null_description() {

        validator = new ItemFormValidator();
        String properDescription = "proper description";
        assertTrue(validator.validateDescription(properDescription));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }


}