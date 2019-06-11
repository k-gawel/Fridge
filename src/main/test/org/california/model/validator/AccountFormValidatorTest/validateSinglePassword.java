package org.california.model.validator.AccountFormValidatorTest;

import org.california.model.validator.AccountFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class validateSinglePassword {

    @Mock
    AccountFormValidator formValidator = new AccountFormValidator();

    private final String valid_string = "valid_string";
    private final String not_valid_string = "not_valid_string";

    @Before
    public void mock_single_string_validation() {
        when(formValidator.validateSingleString(valid_string, "password", 8, 40))
                .thenReturn(true);
        when(formValidator.validateSingleString(not_valid_string, "password", 8, 40))
                .thenReturn(false);
        when(formValidator.validateSinglePassword(any())).thenCallRealMethod();
    }

    @Test
    public void not_valid_string_as_password() {
        String password = not_valid_string;
        assertFalse(formValidator.validateSinglePassword(password));
    }

    @Test
    public void valid_string_as_password() {
        String password = valid_string;
        assertTrue(formValidator.validateSinglePassword(password));
    }

}