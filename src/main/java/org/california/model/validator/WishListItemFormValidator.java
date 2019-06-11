package org.california.model.validator;

public class WishListItemFormValidator extends Validator {

    private Long itemId;
    private Long categoryId;


    public boolean validateCategoryId(Long categoryId) {
        this.categoryId = categoryId == null ? Long.MAX_VALUE : categoryId;

        if(Long.valueOf(Long.MAX_VALUE).equals(this.categoryId) && Long.valueOf(Long.MAX_VALUE).equals(itemId)) {
            setMessage("categoryid_and_itemid.null");
            return false;
        }

        if(this.itemId != null && categoryId != null && !Long.valueOf(Long.MAX_VALUE).equals(itemId)) {
            setMessage("categoryid_and_itemid.notnull");
            return false;
        }

        return true;
    }


    public boolean validateItemId(Long itemId) {
        this.itemId = itemId == null ? Long.MAX_VALUE : itemId;

        if(Long.valueOf(Long.MAX_VALUE).equals(this.categoryId) && this.itemId.equals(Long.MAX_VALUE)) {
            setMessage("categoryid_and_itemid.null");
            return false;
        }

        if(this.categoryId != null && itemId != null && !categoryId.equals(Long.MAX_VALUE)) {
                setMessage("categoryid_and_itemid.notnull");
                return false;
        }

        return true;
    }


    public boolean validateComment(String comment) {
        return validateSingleString(comment, "comment", 0, 350);
    }


    public boolean validateWishListId(Long wishListId) {
        return validateId(wishListId, "wishlist_id");
    }


    public boolean validateAuthorId(Long authorId) {
        return validateId(authorId, "author_id");
    }

}
