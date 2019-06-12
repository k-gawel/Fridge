package org.california.model.transfer.response;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaceDtoTest_builder {

    @Test
    public void nullable_values() {

        PlaceDto.Builder builder;

        builder = new PlaceDto.Builder();
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setAdminId(1L);
        builder.setContainers(new HashSet<>());
        builder.setUsers(new HashSet<>());
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceDto.Builder();
        builder.setAdminId(1L);
        builder.setContainers(new HashSet<>());
        builder.setUsers(new HashSet<>());
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setContainers(new HashSet<>());
        builder.setUsers(new HashSet<>());
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setAdminId(1L);
        builder.setUsers(new HashSet<>());
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setAdminId(1L);
        builder.setContainers(new HashSet<>());
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setAdminId(1L);
        builder.setContainers(new HashSet<>());
        builder.setUsers(new HashSet<>());
        assertThrows(IllegalStateException.class, builder::build);

    }


    @Test
    public void empty_values() {

        PlaceDto.Builder builder = new PlaceDto.Builder();

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setAdminId(1L);
        builder.setContainers(Collections.singleton(new ContainerDto()));
        builder.setUsers(new HashSet<>());
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setAdminId(1L);
        builder.setContainers(new HashSet<>());
        builder.setUsers(Collections.singleton(new PlaceUserDto()));
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setAdminId(1L);
        builder.setContainers(new HashSet<>());
        builder.setUsers(new HashSet<>());
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

    }


    @Test
    public void proper_values() {

        PlaceDto.Builder builder;
        PlaceDto placeDto;

        Collection<ContainerDto> containerDtos = Collections.singleton(new ContainerDto());
        Collection<PlaceUserDto> placeUserDtos = Collections.singleton(new PlaceUserDto());

        placeDto = new PlaceDto();
        placeDto.id = 1L;
        placeDto.adminId = 1L;
        placeDto.containers = containerDtos;
        placeDto.users = placeUserDtos;
        placeDto.name = "name";

        builder = new PlaceDto.Builder();
        builder.setId(1L);
        builder.setAdminId(1L);
        builder.setContainers(containerDtos);
        builder.setUsers(placeUserDtos);
        builder.setName("name");

        assertEquals(placeDto, builder.build());

    }

}