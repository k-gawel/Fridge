package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.WishList;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode @ToString
@Getter
public class PlaceDto implements Serializable {

    private Long id;
    private String name;
    private Long adminId;

    private Collection<ContainerDto> containers;
    private Collection<PlaceUserDto> users;
    private Collection<WishListDto>  wish_lists;

    public static class Builder {
        
        private PlaceDto result = new PlaceDto();

        public NameSetter setId(@NotNull Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Place place) {
            return setId(place.getId());
        }

        public class NameSetter {
            public AdminIdSetter setName(@NotNull String name) {
                Builder.this.result.name = name;
                return new AdminIdSetter();
            }
        }

        public class AdminIdSetter {
            public ContainersSetter setAdminId(@NotNull Long adminId) {
                Builder.this.result.adminId = adminId;
                return new ContainersSetter();
            }

            public ContainersSetter setAdminId(@NotNull Account admin) {
                return setAdminId(admin.getId());
            }
        }

        public class ContainersSetter {
            public WishListsSetter setContainers(@NotEmpty Collection<ContainerDto> containers) {
                Builder.this.result.containers = containers;
                return new WishListsSetter();
            }
        }

        public class WishListsSetter {
            public UsersSetter setWishLists(@NotNull Collection<WishListDto> wishLists) {
                Builder.this.result.wish_lists = wishLists;
                return new UsersSetter();
            }
        }

        public class UsersSetter {
            public FinalBuilder setUsers(@NotEmpty Collection<PlaceUserDto> users) {
                Builder.this.result.users = users;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public PlaceDto build() {
                return Builder.this.result;
            }
        }

    }

}
