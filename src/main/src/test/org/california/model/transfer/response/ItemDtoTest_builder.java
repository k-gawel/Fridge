package org.california.model.transfer.response;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ItemDtoTest_builder {

    @Test
    public void nullable_values() {

        ItemDto.Builder builder = new ItemDto.Builder();

        try {
            builder.build();
            fail("should thrown exception");
        } catch (IllegalStateException e) {
            assertEquals("id", e.getMessage());
        }

        builder.setId(1L);
        try {
            builder.build();
            fail("should thrown exception");
        } catch (IllegalStateException e) {
            assertEquals("name", e.getMessage());
        }

        builder.setName("name");
        try {
            builder.build();
            fail("should thrown exception");
        } catch (IllegalStateException e) {
            assertEquals("category_id", e.getMessage());
        }


    }


    @Test
    public void proper_values() {

        ItemDto.Builder builder = new ItemDto.Builder();
        builder.setId(1L);
        builder.setName("name");
        builder.setCategoryId(1L);

        ItemDto itemDto = new ItemDto();
        itemDto.id = 1L;
        itemDto.name = "name";
        itemDto.categoryId = 1L;

        assertEquals(itemDto, builder.build());

    }


}