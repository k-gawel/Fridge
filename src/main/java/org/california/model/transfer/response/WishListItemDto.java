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

        class WishListIdSetter {
            AuthorIdSetter setWishListId(Long wishListId) {
                Builder.this.result.wishListId = wishListId;
                return new AuthorIdSetter();
            }

            AuthorIdSetter setWishListId(WishList wishList) {
                return setWishListId(wishList != null ? wishList.getId() : null);
            }
        }

        class AuthorIdSetter {
            CreatedOnSetter setAuthorId(Long authorId) {
                Builder.this.result.authorId = authorId;
                return new CreatedOnSetter();
            }

            CreatedOnSetter setAuthorId(Account author) {
                return setAuthorId(author != null ? author.getId() : null);
            }
        }

        class CreatedOnSetter {
            public AddedByIdSetter setCreatedOn(Date createdOn) {
                Builder.this.result.createdOn = createdOn;
                return new AddedByIdSetter();
            }
        }

        class AddedByIdSetter {
            AddedOnSetter setAddedById(Long addedById) {
                Builder.this.result.addedById = addedById;
                return new AddedOnSetter();
            }

            AddedOnSetter setAddedById(Account addedBy) {
                return setAddedById(addedBy != null ? addedBy.getId() : null);
            }
        }

        class AddedOnSetter {
            AddedInstanceIdSetter setAddedOn(Date addedOn) {
                Builder.this.result.addedOn = addedOn;
                return new AddedInstanceIdSetter();
            }
        }

        class AddedInstanceIdSetter {
            CategoryIdSetter setAddedInstanceId(Long addedInstanceId) {
                Builder.this.result.addedInstanceId = addedInstanceId;
                return new CategoryIdSetter();
            }

            CategoryIdSetter setAddedInstanceId(ItemInstance itemInstance) {
                return setAddedInstanceId(itemInstance != null ? itemInstance.getId() : null);
            }
        }

        class CategoryIdSetter {
            ItemIdSetter setCategoryId(Long categoryId) {
                Builder.this.result.categoryId = categoryId;
                return new ItemIdSetter();
            }

            ItemIdSetter setCategoryId(Category category) {
                return setCategoryId(category != null ? category.getId() : null);
            }
        }

        class ItemIdSetter {
            CommentSetter setItemId(Long itemId) {
                Builder.this.result.itemId = itemId;
                return new CommentSetter();
            }

            CommentSetter setItemId(Item item) {
                return setItemId(item != null ? item.getId() : null);
            }
        }

        class CommentSetter {
            public FinalBuilder setComment(String comment) {
                Builder.this.result.comment = comment;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            public WishListItemDto build() {
                return Builder.this.result;
            }
        }

    }

}
