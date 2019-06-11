package org.california.service.model;

import org.california.config.HibernateUtil;
import org.california.config.MyWebAppInitializer;
import org.california.model.transfer.request.AccountForm;
import org.california.util.exceptions.NotValidException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes= { HibernateUtil.class, MyWebAppInitializer.class} )
public class AccountServiceTest_newAccount {


    @Autowired
    AccountService accountService;

    @Test
    public void from_form() {

        Date date = new Date();
        AccountForm accountForm = new AccountForm();

        accountForm.setName("a" + date.getTime());
        accountForm.setEmail("e" + date.getTime());
        accountForm.setPassword1("password1");
        accountForm.setPassword2("password1");

        assertNotNull(accountService.addNewAccount(accountForm).getId());

        accountForm.setEmail("e" + date.getTime());
        assertThrows(NotValidException.class, () -> accountService.addNewAccount(accountForm), "name.exists");

        accountForm.setName("a" + date.getTime());
        assertThrows(NotValidException.class, () -> accountService.addNewAccount(accountForm), "email.exists");

    }




}