package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.transfer.response.AccountDateDto;

import java.util.Date;

@EqualsAndHashCode
@ToString
@Getter
public class WishListItemDto {

    private Long id;

    private Long wishListId;
    private Long authorId;
    private Date createdOn;

    private Long categoryId;
    private String comment;

    private AccountDateDto added;
    private Long addedInstanceId;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface AuthorIdSetter {
            CreatedOnSetter withAuthorId(Long authorId);
        }

        public interface AddedSetter {
            AddedInstanceIdSetter withAdded(AccountDateDto added);
        }

        public interface FinalBuilder {
            WishListItemDto build();
        }

        public interface WishListIdSetter {
            AuthorIdSetter withWishListId(Long wishListId);
        }

        public interface IdSetter {
            WishListIdSetter withId(Long id);
        }

        public interface CreatedOnSetter {
            CategoryIdSetter withCreatedOn(Date createdOn);
        }

        public interface CommentSetter {
            AddedSetter withComment(String comment);
        }

        public interface AddedInstanceIdSetter {
            FinalBuilder withAddedInstanceId(Long addedInstanceId);
        }

        public interface CategoryIdSetter {
            CommentSetter withCategoryId(Long categoryId);
        }

        public static class InnerBuilder implements AuthorIdSetter, AddedSetter, FinalBuilder, WishListIdSetter, IdSetter, CreatedOnSetter, CommentSetter, AddedInstanceIdSetter, CategoryIdSetter {
            private WishListItemDto result = new WishListItemDto();

            public WishListIdSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public AuthorIdSetter withWishListId(Long wishListId) {
                result.wishListId = wishListId;
                return this;
            }

            public CreatedOnSetter withAuthorId(Long authorId) {
                result.authorId = authorId;
                return this;
            }

            public CategoryIdSetter withCreatedOn(Date createdOn) {
                result.createdOn = createdOn;
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
