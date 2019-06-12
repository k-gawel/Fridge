package org.california.model.validator.ItemInstanceFormValidatorTest;

import org.california.model.transfer.request.ItemInstanceForm;
import org.california.model.validator.ItemInstanceFormValidator;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class validate {

    @Test
    public void proper_values() {

        ItemInstanceFormValidator validator = new ItemInstanceFormValidator();
        ItemInstanceForm form = new ItemInstanceForm();

        form.setItemId(1L);
        form.setContainerId(1L);
        form.setExpireDate(new Date());
        form.setComment("comment");

        assertTrue(validator.validate(form));

    }

}