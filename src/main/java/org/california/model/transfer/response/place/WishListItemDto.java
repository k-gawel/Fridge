package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.WishListItem;
import org.california.model.transfer.response.AccountDateDto;
import org.california.model.transfer.response.BaseDto;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class WishListItemDto implements Serializable, BaseDto<WishListItem> {

    private Long id;
    private Long wishListId;

    private AccountDateDto created;

    private Long categoryId;
    private String comment;

    private AccountDateDto added;
    private Long addedInstanceId;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            WishListItemDto build();
        }

        public interface CommentSetter {
            AddedSetter withComment(String comment);
        }

        public interface IdSetter {
            WishListIdSetter withId(Long id);
        }

        public interface AddedInstanceIdSetter {
            FinalBuilder withAddedInstanceId(Long addedInstanceId);
        }

        public interface CategoryIdSetter {
            CommentSetter withCategoryId(Long categoryId);
        }

        public interface WishListIdSetter {
            CreatedSetter withWishListId(Long wishListId);
        }

        public interface AddedSetter {
            AddedInstanceIdSetter withAdded(AccountDateDto added);
        }

        public interface CreatedSetter {
            CategoryIdSetter withCreated(AccountDateDto created);
        }

        public static class InnerBuilder implements FinalBuilder, CommentSetter, IdSetter, AddedInstanceIdSetter, CategoryIdSetter, WishListIdSetter, AddedSetter, CreatedSetter {
            private WishListItemDto result = new WishListItemDto();

            public WishListIdSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public CreatedSetter withWishListId(Long wishListId) {
                result.wishListId = wishListId;
                return this;
            }

            public CategoryIdSetter withCreated(AccountDateDto created) {
                result.created = created;
                return this;
            }

            public CommentSetter withCategoryId(Long categoryId) {
                result.categoryId = categoryId;
                return this;
            }

            public AddedSetter withComment(String comment) {
                result.comment = comment;
                return this;
            }

            public AddedInstanceIdSetter withAdded(AccountDateDto added) {
                result.added = added;
                return this;
            }

            public FinalBuilder withAddedInstanceId(Long addedInstanceId) {
                result.addedInstanceId = addedInstanceId;
                return this;
            }

            public WishListItemDto build() {
                return result;
            }
        }
    }


}
