package org.california.model.transfer.response;

import org.california.model.entity.Account;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ItemInstanceDtoTest_Builder {



    @Test
    public void proper_build() {

        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        Account account = new Account();
        account.setId(1L);


        ItemInstanceDto instanceDto = new ItemInstanceDto();
        instanceDto.id = 1L;
        instanceDto.containerId = 1L;
        instanceDto.itemId = 1L;
        instanceDto.comment = "Comment";
        instanceDto.expireOn = localDate;

        instanceDto.addedById = 1L;
        instanceDto.addedOn = localDate;

        instanceDto.openById = 1L;
        instanceDto.openOn = localDate;

        instanceDto.deletedById = 1L;
        instanceDto.deletedOn = localDate;


    }

    @Test
    public void nullable_values() {

        ItemInstanceDto.Builder builder;

        builder = new ItemInstanceDto.Builder();
        try {
            builder.build();
            fail("should thrown exception");
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "id");
        }

        builder.setId(1L);
        try {
            builder.build();
            fail("should thrown exception");
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "container_id");
        }

        builder.setContainerId(1L);
        try {
            builder.build();
            fail("should thrown exception");
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "added_properties");
        }

        builder.setAddedOn(LocalDate.now());
        try {
            builder.build();
            fail("should thrown exception");
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "added_properties");
        }

        builder.setAddedById(1L);
        assertDoesNotThrow(builder::build);

        builder.setOpenOn(LocalDate.now());
        try {
            builder.build();
            fail("should thrown exception");
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "open_properties");
        }

        builder.setOpenById(1L);
        assertDoesNotThrow(builder::build);

    }

}