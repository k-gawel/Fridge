package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Getter
public class PlaceDto implements Serializable {

    private Long id;
    private String name;
    private Long adminId;

    private Collection<ContainerDto> containers;
    private Collection<PlaceUserDto> users;

    private Collection<WishListDto> wishLists;
    private Collection<ShopListDto> shopLists;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            PlaceDto build();
        }

        public interface AdminIdSetter {
            ContainersSetter withAdminId(Long adminId);
        }

        public interface ShopListsSetter {
            FinalBuilder withShopLists(Collection<ShopListDto> shopLists);
        }

        public interface NameSetter {
            AdminIdSetter withName(String name);
        }

        public interface UsersSetter {
            WishListsSetter withUsers(Collection<PlaceUserDto> users);
        }

        public interface IdSetter {
            NameSetter withId(Long id);
        }

        public interface ContainersSetter {
            UsersSetter withContainers(Collection<ContainerDto> containers);
        }

        public interface WishListsSetter {
            ShopListsSetter withWishLists(Collection<WishListDto> wishLists);
        }

        public static class InnerBuilder implements FinalBuilder, AdminIdSetter, ShopListsSetter, NameSetter, UsersSetter, IdSetter, ContainersSetter, WishListsSetter {
            private PlaceDto result = new PlaceDto();

            public NameSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public AdminIdSetter withName(String name) {
                result.name = name;
                return this;
            }

            public ContainersSetter withAdminId(Long adminId) {
                result.adminId = adminId;
                return this;
            }

            public UsersSetter withContainers(Collection<ContainerDto> containers) {
                result.containers = containers;
                return this;
            }

            public WishListsSetter withUsers(Collection<PlaceUserDto> users) {
                result.users = users;
                return this;
            }

            public ShopListsSetter withWishLists(Collection<WishListDto> wishLists) {
                result.wishLists = wishLists;
                return this;
            }

            public FinalBuilder withShopLists(Collection<ShopListDto> shopLists) {
                result.shopLists = shopLists;
                return this;
            }

            public PlaceDto build() {
                return result;
            }
        }
    }
}
