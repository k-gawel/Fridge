package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class WishListItemForm implements Serializable {

    public final Long categoryId;
    public final Long itemId;
    public final String comment;
    public final Long wishListId;
    public final Long authorId;

    @JsonCreator
    public WishListItemForm(Long categoryId, Long itemId, String comment, Long wishListId, Long authorId) {
        this.categoryId = categoryId;
        this.itemId = itemId;
        this.comment = comment;
        this.wishListId = wishListId;
        this.authorId = authorId;
    }
}
