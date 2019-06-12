package org.california.repository.account;

import org.california.config.HibernateUtil;
import org.california.config.MyWebAppInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes= { HibernateUtil.class, MyWebAppInitializer.class} )
public class AccountRepositoryImplTest_searchByName {


    @Autowired
    AccountRepository accountRepository;

    @Test
    public void nameExists() {

        String name = "roo";

        Map<Serializable, String> result = accountRepository.searchByName(name);

        assertTrue(result instanceof Map);

        result.forEach((k, v)-> System.out.println(k + " : "  + v));

    }

}