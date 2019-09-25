package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.transfer.response.AccountDateDto;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Getter
public class ShopListDto implements Serializable {

    private Long id;
    private AccountDateDto created;

    private boolean status;
    private String description;

    private String shopName;
    private Long placeId;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface PlaceIdSetter {
            FinalBuilder withPlaceId(Long placeId);
        }

        public interface FinalBuilder {
            ShopListDto build();
        }

        public interface IdSetter {
            CreatedSetter withId(Long id);
        }

        public interface ShopNameSetter {
            PlaceIdSetter withShopName(String shopName);
        }

        public interface CreatedSetter {
            StatusSetter withCreated(AccountDateDto created);
        }

        public interface StatusSetter {
            DescriptionSetter withStatus(boolean status);
        }

        public interface DescriptionSetter {
            ShopNameSetter withDescription(String description);
        }

        public static class InnerBuilder implements PlaceIdSetter, FinalBuilder, IdSetter, ShopNameSetter, CreatedSetter, StatusSetter, DescriptionSetter {
            private ShopListDto result = new ShopListDto();

            public CreatedSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public StatusSetter withCreated(AccountDateDto created) {
                result.created = created;
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
