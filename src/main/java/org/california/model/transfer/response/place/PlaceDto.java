package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.Place;
import org.california.model.transfer.response.BaseDto;
import org.california.model.transfer.response.iteminstance.InstanceChangeDto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
public class PlaceDto implements Serializable, BaseDto<Place> {

    private Long id;
    private String name;
    private Long adminId;

    private Collection<ContainerDto> containers;
    private Collection<PlaceUserDto> users;

    private Collection<WishListDto> wishLists;
    private Collection<ShopListDto> shopLists;

    private List<InstanceChangeDto> logs;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface UsersSetter {
            WishListsSetter withUsers(Collection<PlaceUserDto> users);
        }

        public interface FinalBuilder {
            PlaceDto build();
        }

        public interface ShopListsSetter {
            LogsSetter withShopLists(Collection<ShopListDto> shopLists);
        }

        public interface NameSetter {
            AdminIdSetter withName(String name);
        }

        public interface ContainersSetter {
            UsersSetter withContainers(Collection<ContainerDto> containers);
        }

        public interface LogsSetter {
            FinalBuilder withLogs(List<InstanceChangeDto> logs);
        }

        public interface AdminIdSetter {
            ContainersSetter withAdminId(Long adminId);
        }

        public interface WishListsSetter {
            ShopListsSetter withWishLists(Collection<WishListDto> wishLists);
        }

        public interface IdSetter {
            NameSetter withId(Long id);
        }

        public static class InnerBuilder implements UsersSetter, FinalBuilder, ShopListsSetter, NameSetter, ContainersSetter, LogsSetter, AdminIdSetter, WishListsSetter, IdSetter {
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

            public LogsSetter withShopLists(Collection<ShopListDto> shopLists) {
                result.shopLists = shopLists;
                return this;
            }

            public FinalBuilder withLogs(List<InstanceChangeDto> logs) {
                result.logs = logs;
                return this;
            }

            public PlaceDto build() {
                return result;
            }
        }
    }

}
