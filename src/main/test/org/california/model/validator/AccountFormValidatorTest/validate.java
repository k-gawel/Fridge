package org.california.model.validator.AccountFormValidatorTest;

import org.california.model.transfer.request.AccountForm;
import org.california.model.validator.AccountFormValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validate {

    AccountFormValidator validator;

    @Test
    public void proper_values() {

        validator = new AccountFormValidator();
        AccountForm form = new AccountForm();
        form.setName("properName");
        form.setEmail("proper@email.com");
        form.setPassword1("password1");
        form.setPassword2("password1");
        assertTrue(validator.validate(form));

    }


    @Test
    public void not_valid_values() {

        validator = new AccountFormValidator();
        AccountForm form = new AccountForm();
        form.setName("properName");
        form.setEmail("proper@email.com");
        form.setPassword1("password1");
        form.setPassword2("password2");
        assertFalse(validator.validate(form));

    }

}