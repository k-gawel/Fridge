package org.california.service.model.item;

import org.california.config.HibernateUtil;
import org.california.config.MyWebAppInitializer;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.service.getter.ItemGetter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes= { HibernateUtil.class, MyWebAppInitializer.class} )
public class ItemsGetterServiceTest_searchByName {

    @Autowired
    ItemGetter service;

    public ItemsGetterServiceTest_searchByName() {
    }


    @Test
    public void nullOrEmptyValues() {

        Collection<Place> places = null;
        Category category = null;
        String name = null;
        assertTrue(service.searchByName(places, name, category).isEmpty());

        places = new HashSet<>();
        category = new Category();
        name = "";
        assertTrue(service.searchByName(places, name, category).isEmpty());

        places = new HashSet<>();
        category = null;
        name = null;
        assertTrue(service.searchByName(places, name, category).isEmpty());

        places = null;
        category = new Category();
        name = "";
        assertTrue(service.searchByName(places, name, category).isEmpty());

        places = null;
        category = new Category();
        name = null;
        assertTrue(service.searchByName(places, name, category).isEmpty());

        places = null;
        category = new Category();
        name = "";

    }
}