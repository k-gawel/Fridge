package org.california.model.transfer.response;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlaceUserDtoTest_builder {

    @Test
    public void nullable_values() {

        PlaceUserDto.Builder builder;

        builder = new PlaceUserDto.Builder();
        builder.setId(1L);
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceUserDto.Builder();
        builder.setName("name");
        builder.setStatus(false);
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceUserDto.Builder();
        builder.setId(1L);
        builder.setStatus(false);
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceUserDto.Builder();
        builder.setId(1L);
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceUserDto.Builder();
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new PlaceUserDto.Builder();
        builder.setStatus(false);
        assertThrows(IllegalStateException.class, builder::build);

    }


    @Test
    public void proper_values() {

        PlaceUserDto placeUserDto = new PlaceUserDto();
        placeUserDto.id = 1L;
        placeUserDto.name = "name";
        placeUserDto.status = false;

        PlaceUserDto.Builder builder = new PlaceUserDto.Builder();
        builder.setId(1L);
        builder.setName("name");
        builder.setStatus(false);

        assertEquals(placeUserDto, builder.build());
    }


}