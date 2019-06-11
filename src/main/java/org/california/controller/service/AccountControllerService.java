package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.transfer.request.AccountForm;
import org.california.model.transfer.response.EntityToDtoMapper;
import org.california.model.transfer.response.NamedEntityDto;
import org.california.model.validator.AccountFormValidator;
import org.california.model.validator.Validator;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountManagementService;
import org.california.service.model.AccountService;
import org.california.util.exceptions.NotValidException;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AccountControllerService {

    private AccountService accountService;
    private GetterService getterService;
    private AccountManagementService accountManagementService;
    private EntityToDtoMapper mapper;


    @Autowired
    public AccountControllerService(AccountService accountService, GetterService getterService, AccountManagementService accountManagementService, EntityToDtoMapper mapper) {
        this.accountService = accountService;
        this.getterService = getterService;
        this.accountManagementService = accountManagementService;
        this.mapper = mapper;
    }


    public boolean newAccount(AccountForm form) {
        Validator validator = new AccountFormValidator();
        if(!validator.validate(form))
            throw new NotValidException(validator.getMessagesAsString());

        return this.accountService.addNewAccount(form) == null;
    }


    public boolean changeAccountDetails(String token, String password, AccountForm form) {
        Validator validator = new AccountFormValidator();
        if(!validator.validate(form))
            throw new NotValidException(validator.getMessagesAsString());

        Account account = getterService.accounts.getByToken(token);

        if(!account.getPassword().equals(password))
            throw new UnauthorizedException();

        if(!form.getPassword1().equals("password"))
            return accountManagementService.changePassword(account, form.getPassword1());

        if(!form.getEmail().equals("mail@mail.mail"))
            return accountManagementService.changeEmail(account, form.getEmail());

        if(!form.getName().equals("username"))
            return accountManagementService.changeName(account, form.getName());

        return false;
    }


    public Collection<NamedEntityDto> searchByName(String token, String name) {
        Account account = getterService.accounts.getByToken(token);

        if(account == null)
            throw new UnauthorizedException();
        if(name == null)
            throw new NotValidException("name.null");

        return getterService.accounts.searchByName(name).stream()
                .map(mapper::namedEntityToDto)
                .collect(Collectors.toList());
    }

}
