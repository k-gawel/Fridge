package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.ShopList;
import org.california.model.transfer.response.AccountDateDto;
import org.california.model.transfer.response.BaseDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Getter
public class ShopListDto implements Serializable, BaseDto<ShopList> {

    private Long id;

    private Long placeId;
    private AccountDateDto created;

    private boolean status;
    private String description;

    private String shopName;
    private Collection<Number> instances;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface ShopNameSetter {
            InstancesSetter withShopName(String shopName);
        }

        public interface FinalBuilder {
            ShopListDto build();
        }

        public interface IdSetter {
            PlaceIdSetter withId(Long id);
        }

        public interface DescriptionSetter {
            ShopNameSetter withDescription(String description);
        }

        public interface InstancesSetter {
            FinalBuilder withInstances(Collection<Number> instances);
        }

        public interface PlaceIdSetter {
            CreatedSetter withPlaceId(Long placeId);
        }

        public interface CreatedSetter {
            StatusSetter withCreated(AccountDateDto created);
        }

        public interface StatusSetter {
            DescriptionSetter withStatus(boolean status);
        }

        public static class InnerBuilder implements ShopNameSetter, FinalBuilder, IdSetter, DescriptionSetter, InstancesSetter, PlaceIdSetter, CreatedSetter, StatusSetter {
            private ShopListDto result = new ShopListDto();

            public PlaceIdSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public CreatedSetter withPlaceId(Long placeId) {
                result.placeId = placeId;
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

            public InstancesSetter withShopName(String shopName) {
                result.shopName = shopName;
                return this;
            }

            public FinalBuilder withInstances(Collection<Number> instances) {
                result.instances = instances;
                return this;
            }

            public ShopListDto build() {
                return result;
            }
        }
    }


}
