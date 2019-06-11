package org.california.model.validator.ItemFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.ItemFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateSingleAllergen {

    private final int minLength = 3;
    private final int maxLength = 25;

    private ItemFormValidator validator;

    @Test
    public void blank_allergen() {

        validator = new ItemFormValidator();
        String nullAllergen = null;
        assertFalse(validator.validateSingleAllergen(nullAllergen));
        assertTrue(validator.getMessages().contains("allergen.blank"));


        validator = new ItemFormValidator();
        String emptyAllergen = "";
        assertFalse(validator.validateSingleAllergen(emptyAllergen));
        assertTrue(validator.getMessages().contains("allergen.blank"));

    }

    @Test
    public void too_short_allergen() {

        validator = new ItemFormValidator();
        String tooShortAllergen = new String(new char[minLength -1]).replaceAll("\0", "a");
        assertFalse(validator.validateSingleAllergen(tooShortAllergen));
        assertTrue(validator.getMessages().contains("allergen.too_short"));

    }

    @Test
    public void too_long_allergen() {

        validator = new ItemFormValidator();
        String tooLongAllergen = new String(new char[maxLength + 1]).replaceAll("\0", "a");
        assertFalse(validator.validateSingleAllergen(tooLongAllergen));
        assertTrue(validator.getMessages().contains("allergen.too_long"));

    }

    @Test
    public void proper_allergen() {

        validator = new ItemFormValidator();
        String properAllergen = new String(new char[minLength + 1]).replaceAll("\0", "a");
        assertTrue(validator.validateSingleAllergen(properAllergen));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }

}