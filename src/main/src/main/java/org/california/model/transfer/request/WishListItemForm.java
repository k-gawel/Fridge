package org.california.model.transfer.request;

import java.io.Serializable;

public class WishListItemForm implements Serializable {

    private Long categoryId;
    private Long itemId;
    private String comment;
    private Long wishListId;
    private Long authorId;

    public WishListItemForm() {}

    public WishListItemForm(Long categoryId, Long itemId, String comment) {

        this.categoryId = categoryId;
        this.itemId = itemId;
        this.comment = comment;
    }

    public Long getCategoryId() { return categoryId;     }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
