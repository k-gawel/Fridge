package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.validator.AccountFormValidator;
import org.california.repository.account.AccountRepository;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagementService {

    private AccountFormValidator validator;
    private AccountRepository accountRepository;

    @Autowired
    public AccountManagementService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public boolean changeName(Account account, String name) {
        validator = new AccountFormValidator();

        if(account == null)
            throw new NotValidException("account.null");

        if(!validator.validateName(name))
            throw new NotValidException("name.not_valid");

        if(accountRepository.getByName(name) != null)
            throw new NotValidException("name.exists");

        account.setName(name);

        return accountRepository.save(account) != null;
    }


    public boolean changeEmail(Account account, String email) {
        validator = new AccountFormValidator();

        if(account == null)
            throw new NotValidException("account.null");

        if(!validator.validateEmail(email))
            throw new NotValidException("email.not_valid");

        if(accountRepository.getByEmail(email) != null)
            throw new NotValidException("email.exists");

        account.setEmail(email);

        return accountRepository.save(account) != null;
    }


    public boolean changePassword(Account account, String newPassword) {
        validator = new AccountFormValidator();

        if (account == null)
            throw new NotValidException("account.null");

        if (!validator.validateSinglePassword(newPassword))
            throw new NotValidException("password.not_valid");

        account.setPassword(newPassword);

        return accountRepository.save(account) != null;
    }


}
