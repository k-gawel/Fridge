package org.california.model.transfer.response;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerDtoTest_builder {

    @Test
    public void nullable_values() {

        ContainerDto.Builder builder;

        builder = new ContainerDto.Builder();
        builder.setId(1L);
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new ContainerDto.Builder();
        builder.setId(1L);
        builder.setPlaceId(1L);
        assertThrows(IllegalStateException.class, builder::build);

        builder = new ContainerDto.Builder();
        builder.setName("name");
        builder.setPlaceId(1L);
        assertThrows(IllegalStateException.class, builder::build);

    }


    @Test
    public void proper_values() {

        ContainerDto.Builder builder;
        ContainerDto containerDto;

        builder = new ContainerDto.Builder();
        builder.setId(1L);
        builder.setName("name");
        builder.setPlaceId(1L);

        containerDto = new ContainerDto();
        containerDto.id = 1L;
        containerDto.name = "name";
        containerDto.placeId = 1L;

        assertEquals(containerDto, builder.build());
    }

}