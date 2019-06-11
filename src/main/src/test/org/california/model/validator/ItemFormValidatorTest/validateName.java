package org.california.model.validator.ItemFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.ItemFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateName {

    ItemFormValidator validator;

    @Test
    public void blank_name() {


        validator = new ItemFormValidator();
        String nullName = null;
        assertFalse(validator.validateName(nullName));
        assertTrue(validator.getMessages().contains("name.blank"));


        validator = new ItemFormValidator();
        String emptyName = "";
        assertFalse(validator.validateName(emptyName));
        assertTrue(validator.getMessages().contains("name.blank"));

    }

    @Test
    public void too_short_name() {

        validator = new ItemFormValidator();
        String nameShorterThan10 = "dssdf";
        assertFalse(validator.validateName(nameShorterThan10));
        assertTrue(validator.getMessages().contains("name.too_short"));

    }

    @Test
    public void too_long_name() {

        validator = new ItemFormValidator();
        String nameLongerThan140 = new String(new char[141]).replaceAll("\0", "a");
        assertFalse(validator.validateName(nameLongerThan140));
        assertTrue(validator.getMessages().contains("name.too_long"));

    }

    @Test
    public void proper_name() {

        validator = new ItemFormValidator();
        String properName = new String(new char[11]).replaceAll("\0","a");
        assertTrue(validator.validateName(properName));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }


}