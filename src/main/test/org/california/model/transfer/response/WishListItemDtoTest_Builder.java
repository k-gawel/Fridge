package org.california.model.transfer.response;

import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WishListItemDtoTest_Builder {

    @Test
    public void first_nullable() {

        WishListItemDto.Builder builder = new WishListItemDto.Builder();

        assertThrows(IllegalStateException.class, builder::build, "id, wishlist, author or createdon are null");

        builder.setWishListId(1L);
        assertThrows(IllegalStateException.class, builder::build, "id, wishlist, author or createdon are null");

        builder.setAuthorId(1L);
        assertThrows(IllegalStateException.class, builder::build, "id, wishlist, author or createdon are null");

        builder.setCreatedOn(new Date());
        assertThrows(IllegalStateException.class, builder::build, "id, wishlist, author or createdon are null");

    }

    @Test
    public void second_section_test() {

        WishListItemDto.Builder builder = new WishListItemDto.Builder();
        builder.setId(1L);
        builder.setCreatedOn(new Date());
        builder.setAuthorId(1L);
        builder.setWishListId(1L);

        builder.setAddedOn(null);
        builder.setAddedById(null);
        builder.setAddedInstanceId(1L);
        assertThrows(IllegalStateException.class, builder::build, "added instance paremeters must be all null or all not null");

        builder.setAddedOn(null);
        builder.setAddedById(1L);
        builder.setAddedInstanceId(1L);
        assertThrows(IllegalStateException.class, builder::build, "added instance paremeters must be all null or all not null");

        builder.setAddedOn(new Date());
        builder.setAddedById(null);
        builder.setAddedInstanceId(1L);
        assertThrows(IllegalStateException.class, builder::build, "added instance paremeters must be all null or all not null");

        builder.setAddedOn(null);
        builder.setAddedById(1L);
        builder.setAddedInstanceId(null);
        assertThrows(IllegalStateException.class, builder::build, "added instance paremeters must be all null or all not null");

        builder.setAddedOn(new Date());
        builder.setAddedById(null);
        builder.setAddedInstanceId(null);
        assertThrows(IllegalStateException.class, builder::build, "added instance paremeters must be all null or all not null");

        builder.setAddedOn(new Date());
        builder.setAddedById(1L);
        builder.setAddedInstanceId(null);
        assertThrows(IllegalStateException.class, builder::build, "added instance paremeters must be all null or all not null");

//        builder.setAddedOn(new Date());
//        builder.setAddedById(1L);
//        builder.setAddedInstanceId(1L);
//        assertDoesNotThrow(builder::build, "added instance paremeters must be all null or all not null");
//
//        builder.setAddedOn(null);
//        builder.setAddedById(null);
//        builder.setAddedInstanceId(null);
//        assertDoesNotThrow(builder::build, "added instance paremeters must be all null or all not null");

    }

    @Test
    public void second_part() {

        WishListItemDto.Builder builder = new WishListItemDto.Builder();
        builder.setId(1L);
        builder.setCreatedOn(new Date());
        builder.setAuthorId(1L);
        builder.setWishListId(1L);
        builder.setAddedOn(null);
        builder.setAddedById(null);
        builder.setAddedInstanceId(null);

        builder.setItemId(null);
        builder.setCategoryId(null);
        builder.setComment(null);
        assertThrows(IllegalStateException.class, builder::build, "item, category or comment - one of it must not be null");

        builder.setItemId(1L);
        builder.setCategoryId(null);
        builder.setComment(null);
        assertDoesNotThrow(builder::build, "item, category or comment - one of it must not be null");

        builder.setItemId(null);
        builder.setCategoryId(1L);
        builder.setComment(null);
        assertDoesNotThrow(builder::build, "item, category or comment - one of it must not be null");

        builder.setItemId(null);
        builder.setCategoryId(null);
        builder.setComment("comment");
        assertDoesNotThrow(builder::build, "item, category or comment - one of it must not be null");

        builder.setItemId(1L);
        builder.setCategoryId(null);
        builder.setComment("comment");
        assertDoesNotThrow(builder::build, "item, category or comment - one of it must not be null");

        builder.setItemId(null);
        builder.setCategoryId(1L);
        builder.setComment("comment");
        assertDoesNotThrow(builder::build, "item, category or comment - one of it must not be null");

    }



}