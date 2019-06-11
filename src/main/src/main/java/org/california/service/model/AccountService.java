package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.transfer.request.AccountForm;
import org.california.model.validator.AccountFormValidator;
import org.california.repository.account.AccountRepository;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public boolean save(Account account) {
        return accountRepository.save(account) == null;
    }


    public Account addNewAccount(AccountForm form) {
        Account account = formToEntity(form);

        if(accountRepository.getByEmail(account.getEmail()) != null)
            throw new NotValidException("email.exists");
        if(accountRepository.getByName(form.getName()) != null)
            throw new NotValidException("name.exists");

        return accountRepository.save(account);
    }


    public Account formToEntity(AccountForm form) {
        AccountFormValidator validator = new AccountFormValidator();
        if(!validator.validate(form))
            throw new NotValidException(validator.getMessagesAsString());

        Account account = new Account();
        account.setPassword(form.getPassword1());
        account.setCreatedOn(new Date());
        account.setEmail(form.getEmail());
        account.setName(form.getName());

        return account;
    }

}
