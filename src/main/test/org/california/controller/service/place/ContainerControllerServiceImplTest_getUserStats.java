package org.california.controller.service.place;

import org.california.config.HibernateUtil;
import org.california.config.MyWebAppInitializer;
import org.california.controller.service.ContainerControllerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes= { HibernateUtil.class, MyWebAppInitializer.class} )
public class ContainerControllerServiceImplTest_getUserStats {

    @Autowired
    ContainerControllerService containerControllerService;


    @Test
    public void properValues() {

    }

}