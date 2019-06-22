package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.WishList;
import org.california.model.entity.WishListItem;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;

import java.util.Date;

@EqualsAndHashCode @ToString
public class WishListItemDto {
    
    private Long id;
    private Long wishListId;
    private Long authorId;
    private Date createdOn;

    private Long addedById;
    private Date addedOn;
    private Long addedInstanceId;

    private Long categoryId;
    private Long itemId;
    private String comment;


    public static class Builder {
        
        private WishListItemDto result = new WishListItemDto();

        public WishListIdSetter setId(Long id) {
            Builder.this.result.id = id;
            return new WishListIdSetter();
        }

        public WishListIdSetter setId(WishListItem wishListItem) {
            if(wishListItem == null)
                throw new NullPointerException("WishList Item is null");

            return setId(wishListItem.getId());
        }

        public class WishListIdSetter {
            public AuthorIdSetter setWishListId(Long wishListId) {
                Builder.this.result.wishListId = wishListId;
                return new AuthorIdSetter();
            }

            public AuthorIdSetter setWishListId(WishList wishList) {
                return setWishListId(wishList != null ? wishList.getId() : null);
            }
        }

        public class AuthorIdSetter {
            public CreatedOnSetter setAuthorId(Long authorId) {
                Builder.this.result.authorId = authorId;
                return new CreatedOnSetter();
            }

            public CreatedOnSetter setAuthorId(Account author) {
                return setAuthorId(author != null ? author.getId() : null);
            }
        }

        public class CreatedOnSetter {
            public AddedByIdSetter setCreatedOn(Date createdOn) {
                Builder.this.result.createdOn = createdOn;
                return new AddedByIdSetter();
            }
        }

        public class AddedByIdSetter {
            public AddedOnSetter setAddedById(Long addedById) {
                Builder.this.result.addedById = addedById;
                return new AddedOnSetter();
            }

            public AddedOnSetter setAddedById(Account addedBy) {
                return setAddedById(addedBy != null ? addedBy.getId() : null);
            }
        }

        public class AddedOnSetter {
            public AddedInstanceIdSetter setAddedOn(Date addedOn) {
                Builder.this.result.addedOn = addedOn;
                return new AddedInstanceIdSetter();
            }
        }

        public class AddedInstanceIdSetter {
            public CategoryIdSetter setAddedInstanceId(Long addedInstanceId) {
                Builder.this.result.addedInstanceId = addedInstanceId;
                return new CategoryIdSetter();
            }

            public CategoryIdSetter setAddedInstanceId(ItemInstance itemInstance) {
                return setAddedInstanceId(itemInstance != null ? itemInstance.getId() : null);
            }
        }

        public class CategoryIdSetter {
            public ItemIdSetter setCategoryId(Long categoryId) {
                Builder.this.result.categoryId = categoryId;
                return new ItemIdSetter();
            }

            public ItemIdSetter setCategoryId(Category category) {
                return setCategoryId(category != null ? category.getId() : null);
            }
        }

        public class ItemIdSetter {
            public CommentSetter setItemId(Long itemId) {
                Builder.this.result.itemId = itemId;
                return new CommentSetter();
            }

            public CommentSetter setItemId(Item item) {
                return setItemId(item != null ? item.getId() : null);
            }
        }

        public class CommentSetter {
            public FinalBuilder setComment(String comment) {
                Builder.this.result.comment = comment;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public WishListItemDto build() {
                return Builder.this.result;
            }
        }

    }

}
