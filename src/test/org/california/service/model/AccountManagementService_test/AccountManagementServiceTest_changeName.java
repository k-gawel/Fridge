package org.california.service.model.AccountManagementService_test;

import org.california.model.entity.Account;
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
public class AccountManagementServiceTest_changeName {

    @Mock
    AccountRepository accountRepository;
    @Mock
    AccountFormValidator validator;
    @InjectMocks
    AccountManagementService accountManagementService;

    @Before
    public void mock_validator() throws Exception {
        when(validator.validateName("valid_name")).thenReturn(true);
        when(validator.validateName("not_valid_name")).thenReturn(false);

        when(validator.validateName("existing_name")).thenReturn(true);
        when(validator.validateName("not_existing_name")).thenReturn(true);

        PowerMockito.whenNew(AccountFormValidator.class).withNoArguments().thenReturn(validator);
    }

    @Before
    public void mock_repository_get_by_name() {
        when(accountRepository.getByName("existing_name")).thenReturn(new Account());
        when(accountRepository.getByName("not_existing_name")).thenReturn(null);
    }


    @Test
    public void not_valid_params() throws Exception {

        Executable executable;
        Class<? extends Throwable> errorClass;
        String errorMessage;


        // NOT-NULL Account, NOT-VALID NAME
        executable = () -> accountManagementService.changeName(new Account(), "not_valid_name");
        errorClass = NotValidException.class;
        errorMessage = "name.not_valid";
        assertThrowsMessage(errorClass, executable, errorMessage);

        // NULL Account, VALID NAME
        executable = () -> accountManagementService.changeName(null, "valid_name");
        errorClass = NotValidException.class;
        errorMessage = "account.null";
        assertThrowsMessage(errorClass, executable, errorMessage);

        // NULL Account, NOT-VALID NAME
        executable = () -> accountManagementService.changeName(null, "not_valid_name");
        errorClass = NotValidException.class;
        errorMessage = "account.null";
        assertThrowsMessage(errorClass, executable, errorMessage);

    }


    @Test
    public void name_exists_or_not() {
        Executable executable;
        Class<? extends Throwable> errorClass;
        String errorMessage;

        // valid params but name already exists in database
        executable = () -> accountManagementService.changeName(new Account(), "existing_name");
        errorClass = NotValidException.class;
        errorMessage = "name.exists";
        assertThrowsMessage(errorClass, executable, errorMessage);

        // valid params and name doesn't exists in database
        executable = () -> accountManagementService.changeName(new Account(), "not_existing_name");
        assertDoesNotThrow(executable);

    }


}