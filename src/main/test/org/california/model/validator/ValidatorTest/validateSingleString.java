package org.california.model.validator.ValidatorTest;

import org.california.model.validator.Validator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateSingleString {

    private Validator validator;

    @Test
    public void blank_string() {
        validator = new Validator();
        String nullString = null;
        assertFalse(validator.validateSingleString(nullString, "string", 4, 5));
        assertTrue(validator.getMessages().contains("string.blank"));

        validator = new Validator();
        String emptyString = "";
        assertFalse(validator.validateSingleString(emptyString, "string", 4, 5));
        assertTrue(validator.getMessages().contains("string.blank"));
    }


    @Test
    public void too_long_string() {
        validator = new Validator();
        String stringOfLength7 = "1234567";
        assertFalse(validator.validateSingleString(stringOfLength7, "string", 1, 6));
        assertTrue(validator.getMessages().contains("string.too_long"));
    }


    @Test
    public void too_short_string() {
        validator = new Validator();
        String stringOfLength5 = "12345";
        assertFalse(validator.validateSingleString(stringOfLength5, "string", 6, 10));
        assertTrue(validator.getMessages().contains("string.too_short"));
    }


    @Test
    public void fitting_string() {
        validator = new Validator();
        String stringOfLength5 = "12345";
        assertTrue(validator.validateSingleString(stringOfLength5, "string", 3, 7));
    }

}