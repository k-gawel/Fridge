package org.california.service.model.place;

import org.california.config.HibernateUtil;
import org.california.config.MyWebAppInitializer;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.transfer.request.PlaceForm;
import org.california.service.model.AccountService;
import org.california.service.model.PlaceService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes= { HibernateUtil.class, MyWebAppInitializer.class} )
public class PlaceServiceImplTest_usersManagement {

    @Autowired
    AccountService accountService;
    @Autowired
    PlaceService placeService;

    Place place;
    Account account1;
    Account account2;

    @Before
    public void set_place_and_accounts() {

        Date date = new Date();

        account1 = new Account();
        account1.setName("testAccount1" + date.getTime());
        account1.setEmail(date.getTime() + "test1@email.com");
        account1.setPassword("password1");
        account1.setCreatedOn(date);
        
        account2 = new Account();
        account2.setName("testAccount2" + date.getTime());
        account2.setEmail(date.getTime() + "test2@email.com");
        account2.setPassword("password2");
        account2.setCreatedOn(date);
        
        accountService.save(account1);
        accountService.save(account2);

        PlaceForm placeForm = new PlaceForm();
        placeForm.setName("test place " + date.toString());
        place = placeService.newPlace(account1, placeForm);

        assertNotNull(account1.getId());
        assertNotNull(account2.getId());
        assertNotNull(place.getId());
    }





    @Test
    public void add_and_remove_user() {
        assertFalse(place.getAccounts().contains(account2));
        assertTrue(placeService.newUser(place, account2));
        assertTrue(place.getAccounts().contains(account2));
        assertTrue(placeService.removeUser(place, account2));
        assertFalse(place.getAccounts().contains(account2));
        assertTrue(place.getUnaactiveAccounts().contains(account2));
    }


    @Test
    public void remove_admin_when_is_alone() {
        assertEquals(place.getAdmin(), account1);
        assertEquals(place.getAccounts().size(), 1);
        assertTrue(placeService.removeUser(place, account1));
        assertFalse(place.getAccounts().contains(account1));
    }


    @Test
    public void remove_admin_when_not_alone() {

        placeService.newUser(place, account2);
        assertTrue(place.getAccounts().size() > 1);
        assertEquals(place.getAdmin(), account1);
        assertFalse(placeService.removeUser(place, account1));
        assertEquals(place.getAdmin(), account1);
        assertTrue(place.getAccounts().contains(account1));

    }


    @Test
    public void change_admin_for_existing() {

        assertTrue(place.getAccounts().contains(account1));
        assertEquals(place.getAdmin(), account1);
        assertEquals(place.getAccounts().size(), 1);
        assertFalse(placeService.changeAdmin(place, account1));
        assertEquals(place.getAdmin(), account1);

        placeService.newUser(place, account2);
        assertTrue(place.getAccounts().contains(account2));
        assertEquals(place.getAdmin(), account1);
        assertEquals(place.getAccounts().size(), 2);
        assertFalse(placeService.changeAdmin(place, account1));

    }

    @Test
    public void change_admin_for_nonactvie_account() {

        assertTrue(place.getAccounts().contains(account1));
        assertFalse(place.getAccounts().contains(account2));
        assertEquals(place.getAdmin(), account1);
        assertFalse(placeService.changeAdmin(place, account2));
        assertEquals(place.getAdmin(), account1);

    }


    @Test
    public void change_admin_for_active_account() {

        placeService.newUser(place, account2);

        assertTrue(place.getAccounts().contains(account2));
        assertNotEquals(place.getAdmin(), account2);
        assertTrue(placeService.changeAdmin(place, account2));
        assertEquals(place.getAdmin(), account2);
    }


    @After
    public void delete_accounts_and_place() {
        account1 = null;
        account2 = null;
        place = null;
        assertNull(account1);
        assertNull(account2);
        assertNull(place);
    }
}