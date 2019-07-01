package org.california.service.model.AccountManagementService_test;

import org.california.model.entity.Account;
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

import static org.powermock.api.mockito.PowerMockito.when;
import static util.TestUtil.assertThrowsMessage;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AccountManagementService.class)
class AccountManagementServiceTest_changePassword {

    @Mock
    AccountFormValidator validator;
    @InjectMocks
    AccountManagementService service;

    @Before
    public void mock_validator() throws Exception {
        when(validator.validateSinglePassword("valid_password")).thenReturn(true);
        when(validator.validateSinglePassword("not_valid_password")).thenReturn(false);
        PowerMockito.whenNew(AccountFormValidator.class).withNoArguments().thenReturn(validator);
    }

    Executable executable;
    Class<? extends Throwable> errorClass;
    String errorMessage;


    @Test
    public void not_valid_parameters() {

        // NULL Account, VALID PASSWORD
        executable = () -> service.changePassword(null, "valid_password");
        errorClass = NotValidException.class;
        errorMessage = "account.null";
        assertThrowsMessage(errorClass, executable, errorMessage);

        // NOT-NULL Account, NOT-VALID PASSWORD
        executable = () -> service.changePassword(new Account(), "not_valid_password");
        errorClass = NotValidException.class;
        errorMessage = "password.not_valid";
        assertThrowsMessage(errorClass, executable, errorMessage);

        // NULL Account, NOT-VALID PASSWORD
        executable = () -> service.changePassword(null, "not_valid_password");
        errorClass = NotValidException.class;
        errorMessage = "account.null";
        assertThrowsMessage(errorClass, executable, errorMessage);

    }

}