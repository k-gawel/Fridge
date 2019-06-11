package org.california.model.validator.ItemFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.ItemFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateStorage {

    ItemFormValidator validator;


    @Test
    public void too_long_storage() {

        validator = new ItemFormValidator();
        String tooLongStorage = new String(new char[1501]).replaceAll("\0", "a");
        assertFalse(validator.validateStorage(tooLongStorage));
        assertTrue(validator.getMessages().contains("storage.too_long"));

    }


    @Test
    public void blank_storage() {

        validator = new ItemFormValidator();
        String nullStorage = null;
        assertTrue(validator.validateStorage(nullStorage));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

        validator = new ItemFormValidator();
        String empty = "";
        assertTrue(validator.validateStorage(empty));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }


    @Test
    public void not_null_storage() {

        validator = new ItemFormValidator();
        String properStorage = "proper storage";
        assertTrue(validator.validateStorage(properStorage));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }
    
}