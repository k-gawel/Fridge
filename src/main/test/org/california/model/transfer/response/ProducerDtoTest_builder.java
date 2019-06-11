package org.california.model.transfer.response;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProducerDtoTest_builder {

    @Test
    public void nullable_values() {

        ProducerDto.Builder builder;

        builder = new ProducerDto.Builder();
        builder.setName("name");
        assertThrows(IllegalStateException.class, builder::build);

        builder = new ProducerDto.Builder();
        builder.setId(1L);
        assertThrows(IllegalStateException.class, builder::build);

    }

    @Test
    public void proper_values() {

        ProducerDto producerDto;
        ProducerDto.Builder builder;


        producerDto = new ProducerDto();
        producerDto.id = 1L;
        producerDto.name = "name";

        builder = new ProducerDto.Builder();
        builder.setId(1L);
        builder.setName("name");

        assertEquals(builder.build(), producerDto);

        producerDto = new ProducerDto();
        builder = new ProducerDto.Builder();

        assertEquals(producerDto, builder.build());

    }




}