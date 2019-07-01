package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.transfer.request.validator.UniqueField;
import org.california.repository.account.AccountRepository;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagementService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountManagementService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public boolean changeName(Account account, String name) {
        account.setName(name);

        return accountRepository.save(account) != null;
    }


    public boolean changeEmail(Account account, String email) {
        account.setEmail(email);

        return accountRepository.save(account) != null;
    }


    public boolean changePassword(Account account, String newPassword) {
        account.setPassword(newPassword);

        return accountRepository.save(account) != null;
    }


}
