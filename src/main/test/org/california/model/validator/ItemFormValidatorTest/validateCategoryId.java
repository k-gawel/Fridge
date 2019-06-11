package org.california.model.validator.ItemFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.validator.ItemFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateCategoryId {

    ItemFormValidator validator;

    @Test
    public void nullable_category_id() {

        validator = new ItemFormValidator();
        Long nullId = null;
        assertFalse(validator.validateCategoryId(nullId));
        assertTrue(validator.getMessages().contains("category_id.empty"));

    }


    @Test
    public void proper_category_id() {

        validator = new ItemFormValidator();
        Long properId = 1L;
        assertTrue(validator.validateCategoryId(properId));
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));

    }

}