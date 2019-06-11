package org.california.service.model.AccountManagementService_test;

import org.california.model.entity.Account;
import org.california.model.validator.AccountFormValidator;
import org.california.repository.account.AccountRepository;
import org.california.service.model.AccountManagementService;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.powermock.api.mockito.PowerMockito.when;
import static util.TestUtil.assertThrowsMessage;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccountManagementService.class)
public class AccountManagementServiceTest_changeEmail {

    @Mock
    AccountFormValidator validator;
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountManagementService accountManagementService;

    Executable executable;
    Class<? extends Throwable> errorClass;
    String errorMessage;

    @Before
    public void mock_validator() throws Exception {
        when(validator.validateEmail("not_valid_email")).thenReturn(false);
        when(validator.validateEmail("valid_email")).thenReturn(true);
        when(validator.validateEmail("existing_email")).thenReturn(true);
        when(validator.validateEmail("not_existing_email")).thenReturn(true);

        PowerMockito.whenNew(AccountFormValidator.class).withNoArguments().thenReturn(validator);
    }

    @Before
    public void mock_repository() {
        when(accountRepository.getByEmail("existing_email")).thenReturn(new Account());
        when(accountRepository.getByEmail("not_existing_email")).thenReturn(null);
    }


    @Test
    public void not_valid_params() {

        // NULL ACCOUNT, NOT-VALID EMAIL
        executable = () -> accountManagementService.changeEmail(null, "not_valid_email");
        errorClass = NotValidException.class;
        errorMessage = "account.null";
        assertThrowsMessage(errorClass, executable, errorMessage);


        // NOT-NULL ACCOUNT, NOT_VALID EMAIL
        executable = () -> accountManagementService.changeEmail(new Account(), "not_valid_email");
        errorClass = NotValidException.class;
        errorMessage = "email.not_valid";
        assertThrowsMessage(errorClass, executable, errorMessage);


        // NULL ACCOUNT, VALID EMAIL
        executable = () -> accountManagementService.changeEmail(null, "valid_email");
        errorClass = NotValidException.class;
        errorMessage = "account.null";
        assertThrowsMessage(errorClass, executable, errorMessage);


    }


    @Test
    public void email_exists_or_not() {

        // VALID PARAMS BUT EMAIL EXISTS
        executable = () -> accountManagementService.changeEmail(new Account(), "existing_email");
        errorClass = NotValidException.class;
        errorMessage = "email.exists";
        assertThrowsMessage(errorClass, executable, errorMessage);

        // VALID PARAMS AND EMAIL IS FREE
        executable = () -> accountManagementService.changeEmail(new Account(), "not_existing_email");
        assertDoesNotThrow(executable);

    }

}