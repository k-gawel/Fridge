package org.california.model.validator.ContainerFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.ContainerFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateName {


    ContainerFormValidator validator;

    @Test
    public void not_valid_name() {

        validator = new ContainerFormValidator();
        String tooShortName = "abcd";
        assertFalse(validator.validateName(tooShortName));
        assertTrue(validator.getMessagesAsString().contains("name.too_short"));

        validator = new ContainerFormValidator();
        String tooLongName = "abcdabcdabcdabcdabcdabcd";
        assertFalse(validator.validateName(tooLongName));
        assertTrue(validator.getMessagesAsString().contains("name.too_long"));

        validator = new ContainerFormValidator();
        String nullName = null;
        assertFalse(validator.validateName(nullName));
        assertTrue(validator.getMessages().contains("name.blank"));

        validator = new ContainerFormValidator();
        String emptyName = "";
        assertFalse(validator.validateName(emptyName));
        assertTrue(validator.getMessages().contains("name.blank"));

    }


    @Test
    public void proper_name() {

        validator = new ContainerFormValidator();
        String properName = "properName";
        assertTrue(validator.validateName(properName));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }

}