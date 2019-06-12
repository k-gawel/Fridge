package org.california.service.model.AccountServiceTest;

import org.california.model.entity.Account;
import org.california.model.transfer.request.AccountForm;
import org.california.model.validator.AccountFormValidator;
import org.california.service.model.AccountService;
import org.california.util.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccountService.class)
public class accountFormToEntity {

    @Mock
    AccountFormValidator validator;
    @InjectMocks
    AccountService service;

    AccountForm validForm = new AccountForm();
    AccountForm notValidForm = new AccountForm();

    Executable executable;
    Class<? extends Throwable> errorClass;
    String errorMessage;

    @Before
    public void mock_validator() throws Exception {
        when(validator.validate(validForm)).thenReturn(true);
        when(validator.validate(notValidForm)).thenReturn(false);
        PowerMockito.whenNew(AccountFormValidator.class).withNoArguments().thenReturn(validator);
    }

    @Test
    public void not_valid_form() {

        // NOT VALID FORM
        executable = () -> service.formToEntity(notValidForm);
        errorClass = NotValidException.class;
        errorMessage = "form.not_valid";
        assertThrows(errorClass, executable);

    }

    @Test
    public void valid_form() {

        // VALID FORM
        executable = () -> service.formToEntity(validForm);
        assertDoesNotThrow(executable);

        AccountForm form = new AccountForm();

        String name = "properName";
        String email = "proper@email.com";
        String password1 = "properPassword";
        String password2 = "properPassword";

        form.setName(name);
        form.setEmail(email);
        form.setPassword1(password1);
        form.setPassword2(password2);


        when(validator.validate(form)).thenReturn(true);

        Account account = service.formToEntity(form);
        assertEquals(account.getEmail(), email);
        assertEquals(account.getName(), name);
        assertEquals(account.getPassword(), password1);
    }


}