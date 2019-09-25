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
    private Collection<WishListDto> wish_lists;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface UsersSetter {
            Wish_listsSetter withUsers(Collection<PlaceUserDto> users);
        }

        public interface FinalBuilder {
            PlaceDto build();
        }

        public interface Wish_listsSetter {
            FinalBuilder withWishLists(Collection<WishListDto> wish_lists);
        }

        public interface NameSetter {
            AdminIdSetter withName(String name);
        }

        public interface AdminIdSetter {
            ContainersSetter withAdminId(Long adminId);
        }

        public interface ContainersSetter {
            UsersSetter withContainers(Collection<ContainerDto> containers);
        }

        public interface IdSetter {
            NameSetter withId(Long id);
        }

        public static class InnerBuilder implements UsersSetter, FinalBuilder, Wish_listsSetter, NameSetter, AdminIdSetter, ContainersSetter, IdSetter {
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

            public Wish_listsSetter withUsers(Collection<PlaceUserDto> users) {
                result.users = users;
                return this;
            }

            public FinalBuilder withWishLists(Collection<WishListDto> wish_lists) {
                result.wish_lists = wish_lists;
                return this;
            }

            public PlaceDto build() {
                return result;
            }
        }
    }


}
