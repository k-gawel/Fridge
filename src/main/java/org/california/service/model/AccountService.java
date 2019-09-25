package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.transfer.request.forms.AccountForm;
import org.california.repository.account.AccountRepository;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

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
        if (accountRepository.getByName(form.name).isEmpty())
            throw new NotValidException("name.exists");

        return accountRepository.save(account);
    }


    public Account formToEntity(AccountForm form) {

        Account account = new Account();
        account.setPassword(form.password1);
        account.setCreatedOn(new Date());
        account.setEmail(form.email);
        account.setName(form.name);

        return account;
    }

}
