package org.california.model.transfer.response;

import java.util.Date;

public class WishListItemDto {
    
    public Long id;
    public Long wishListId;
    public Long authorId;
    public Date createdOn;

    public Long addedById;
    public Date addedOn;
    public Long addedInstanceId;

    public Long categoryId;
    public Long itemId;
    public String comment;


    public static class Builder {
        
        private WishListItemDto result = new WishListItemDto();

        public void setId(Long id) {
            result.id = id;
        }

        public void setWishListId(Long wishListId) {
            result.wishListId = wishListId;
        }

        public void setAuthorId(Long authorId) {
            result.authorId = authorId;
        }

        public void setCreatedOn(Date createdOn) {
            result.createdOn = createdOn;
        }

        public void setAddedById(Long addedById) {
            result.addedById = addedById;
        }

        public void setAddedOn(Date addedOn) {
            result.addedOn = addedOn;
        }

        public void setAddedInstanceId(Long addedInstanceId) {
            result.addedInstanceId = addedInstanceId;
        }

        public void setCategoryId(Long categoryId) {
            result.categoryId = categoryId;
        }

        public void setItemId(Long itemId) {
            result.itemId = itemId;
        }

        public void setComment(String comment) {
            result.comment = comment;
        }


        public WishListItemDto build() {
            if(result.id == null || result.wishListId == null ||
                    result.authorId == null || result.createdOn == null)
                throw new IllegalStateException("id, wishlist, author or createdon are null");

            if((result.addedOn != null) == (result.addedById != null) == (result.addedInstanceId != null))
                throw new IllegalStateException("added instance paremeters must be all null or all not null");

            if(result.categoryId == null && result.itemId == null && result.comment == null)
                throw new IllegalStateException("item, category or comment - one of it must not be null");

            return result;
        }

    }
    
    
    

}
