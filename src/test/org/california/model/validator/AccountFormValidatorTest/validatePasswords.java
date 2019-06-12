package org.california.model.validator.AccountFormValidatorTest;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.transfer.request.AccountForm;
import org.california.model.validator.AccountFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class validatePasswords {

    @Mock
    AccountFormValidator validator;


    String validName = "validname";
    String validEmail = "email@mail.com";
    AccountForm form;

    @Before
    public void mock_email_and_name_validation() {
        when(validator.validateEmail(any())).thenReturn(true);
        when(validator.validateName(any())).thenReturn(true);
        when(validator.validatePassword1(any())).thenCallRealMethod();
        when(validator.validatePassword2(any())).thenCallRealMethod();
        when(validator.validate(any())).thenCallRealMethod();
        when(validator.validateSinglePassword("not_valid_password")).thenReturn(false);
        when(validator.validateSinglePassword("valid_password")).thenReturn(true);
        when(validator.validateSinglePassword(not(eq("not_valid_password")))).thenReturn(true);
    }

    @Test
    public void nullable_passwords() {
        validator = new AccountFormValidator();
        form = new AccountForm();
        form.setPassword1("not_null_password");
        form.setPassword2(null);
        assertFalse(validator.validate(form));

        validator = new AccountFormValidator();
        form = new AccountForm();
        form.setPassword1(null);
        form.setPassword2("not_null_password");
        assertFalse(validator.validate(form));

        validator = new AccountFormValidator();
        form = new AccountForm();
        form.setPassword1(null);
        form.setPassword2(null);
        assertFalse(validator.validate(form));

    }


    @Test
    public void not_valid_passwords() {
        validator = new AccountFormValidator();
        form = new AccountForm();
        form.setPassword1("not_valid_password");
        form.setPassword2("not_valid_password");
        assertFalse(validator.validate(form));
    }


    @Test
    public void not_equals_passwords() {
        form = new AccountForm();
        form.setPassword1("validpassword1");
        form.setPassword2("validpassword2");
        assertFalse(validator.validate(form));
    }

    @Test
    public void proper_passwords() {
        form = new AccountForm();
        form.setPassword1("properpassword");
        form.setPassword2("properpassword");
        assertTrue(validator.validate(form), validator.getMessagesAsString());
        assertTrue(CollectionUtils.isEmpty(validator.getMessages()));
    }

}