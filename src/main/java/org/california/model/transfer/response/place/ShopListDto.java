package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Getter
public class ShopListDto implements Serializable {

    private Long id;
    private LocalDate createdOn;
    private boolean status;
    private String description;
    private String shopName;
    private Long placeId;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            ShopListDto build();
        }

        public interface IdSetter {
            CreatedOnSetter withId(Long id);
        }

        public interface DescriptionSetter {
            ShopNameSetter withDescription(String description);
        }

        public interface StatusSetter {
            DescriptionSetter withStatus(boolean status);
        }

        public interface ShopNameSetter {
            PlaceIdSetter withShopName(String shopName);
        }

        public interface PlaceIdSetter {
            FinalBuilder withPlaceId(Long placeId);
        }

        public interface CreatedOnSetter {
            StatusSetter withCreatedOn(LocalDate createdOn);
        }

        public static class InnerBuilder implements FinalBuilder, IdSetter, DescriptionSetter, StatusSetter, ShopNameSetter, PlaceIdSetter, CreatedOnSetter {
            private ShopListDto result = new ShopListDto();

            public CreatedOnSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public StatusSetter withCreatedOn(LocalDate createdOn) {
                result.createdOn = createdOn;
                return this;
            }

            public DescriptionSetter withStatus(boolean status) {
                result.status = status;
                return this;
            }

            public ShopNameSetter withDescription(String description) {
                result.description = description;
                return this;
            }

            public PlaceIdSetter withShopName(String shopName) {
                result.shopName = shopName;
                return this;
            }

            public FinalBuilder withPlaceId(Long placeId) {
                result.placeId = placeId;
                return this;
            }

            public ShopListDto build() {
                return result;
            }
        }
    }


}
