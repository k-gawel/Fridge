package org.california.service.model.place;

import org.california.config.HibernateUtil;
import org.california.config.MyWebAppInitializer;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.model.transfer.response.ContainerDto;
import org.california.model.transfer.response.EntityToDtoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes= { HibernateUtil.class, MyWebAppInitializer.class} )
public class ContainerServiceImplTest_toDto {

    @Autowired
    private EntityToDtoMapper mapper;

    @Test
    public void nullable_values() {

        Container container = null;
        assertThrows(NullPointerException.class, () -> mapper.containerToDto(container));
    }

    @Test
    public void proper_values() {

        Container container = new Container();
        container.setId(1L);
        Place place = new Place();
        place.setId(1L);
        container.setPlace(place);
        container.setName("name");

        ContainerDto containerDto = new ContainerDto();
        containerDto.id = 1L;
        containerDto.placeId = 1L;
        containerDto.name = "name";

        assertEquals(mapper.containerToDto(container), containerDto);
    }


}