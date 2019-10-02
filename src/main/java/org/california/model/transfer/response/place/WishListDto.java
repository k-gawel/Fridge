package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.WishList;
import org.california.model.entity.utils.AccountDate;
import org.california.model.transfer.response.AccountDateDto;
import org.california.model.transfer.response.BaseDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Getter
public class WishListDto implements Serializable, BaseDto<WishList> {

    private Long id;
    private Long placeId;
    private boolean status;

    private AccountDateDto created;
    private AccountDateDto archived;

    private String name;
    private String description;

    private Collection<WishListItemDto> items;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            WishListDto build();
        }

        public interface ItemsSetter {
            FinalBuilder withItems(Collection<WishListItemDto> items);
        }

        public interface IdSetter {
            PlaceIdSetter withId(Long id);
        }

        public interface DescriptionSetter {
            ItemsSetter withDescription(String description);
        }

        public interface StatusSetter {
            CreatedSetter withStatus(boolean status);
        }

        public interface ArchivedSetter {
            NameSetter withArchived(AccountDateDto archived);
        }

        public interface PlaceIdSetter {
            StatusSetter withPlaceId(Long placeId);
        }

        public interface CreatedSetter {
            ArchivedSetter withCreated(AccountDateDto created);
        }

        public interface NameSetter {
            DescriptionSetter withName(String name);
        }

        public static class InnerBuilder implements FinalBuilder, ItemsSetter, IdSetter, DescriptionSetter, StatusSetter, ArchivedSetter, PlaceIdSetter, CreatedSetter, NameSetter {
            private WishListDto result = new WishListDto();

            public PlaceIdSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public StatusSetter withPlaceId(Long placeId) {
                result.placeId = placeId;
                return this;
            }

            public CreatedSetter withStatus(boolean status) {
                result.status = status;
                return this;
            }

            public ArchivedSetter withCreated(AccountDateDto created) {
                result.created = created;
                return this;
            }

            public NameSetter withArchived(AccountDateDto archived) {
                result.archived = archived;
                return this;
            }

            public DescriptionSetter withName(String name) {
                result.name = name;
                return this;
            }

            public ItemsSetter withDescription(String description) {
                result.description = description;
                return this;
            }

            public FinalBuilder withItems(Collection<WishListItemDto> items) {
                result.items = items;
                return this;
            }

            public WishListDto build() {
                return result;
            }
        }
    }


}