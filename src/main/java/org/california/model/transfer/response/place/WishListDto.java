package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Getter
public class WishListDto implements Serializable {

    private Long id;
    private Long placeId;
    private boolean status;

    private LocalDate createdOn;
    private LocalDate archivedOn;

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

        public interface PlaceIdSetter {
            StatusSetter withPlaceId(Long placeId);
        }

        public interface ArchivedOnSetter {
            NameSetter withArchivedOn(LocalDate archivedOn);
        }

        public interface DescriptionSetter {
            ItemsSetter withDescription(String description);
        }

        public interface NameSetter {
            DescriptionSetter withName(String name);
        }

        public interface ItemsSetter {
            FinalBuilder withItems(Collection<WishListItemDto> items);
        }

        public interface CreatedOnSetter {
            ArchivedOnSetter withCreatedOn(LocalDate createdOn);
        }

        public interface StatusSetter {
            CreatedOnSetter withStatus(boolean status);
        }

        public interface IdSetter {
            PlaceIdSetter withId(Long id);
        }

        public static class InnerBuilder implements FinalBuilder, PlaceIdSetter, ArchivedOnSetter, DescriptionSetter, NameSetter, ItemsSetter, CreatedOnSetter, StatusSetter, IdSetter {
            private WishListDto result = new WishListDto();

            public PlaceIdSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public StatusSetter withPlaceId(Long placeId) {
                result.placeId = placeId;
                return this;
            }

            public CreatedOnSetter withStatus(boolean status) {
                result.status = status;
                return this;
            }

            public ArchivedOnSetter withCreatedOn(LocalDate createdOn) {
                result.createdOn = createdOn;
                return this;
            }

            public NameSetter withArchivedOn(LocalDate archivedOn) {
                result.archivedOn = archivedOn;
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