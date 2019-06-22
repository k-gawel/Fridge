package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode @ToString
public class PlaceDto implements Serializable {

    private Long id;
    private String name;
    private Long adminId;

    private Collection<ContainerDto> containers;
    private Collection<PlaceUserDto> users;


    public static class Builder {
        
        private PlaceDto result = new PlaceDto();

        public NameSetter setId(@NotNull Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Place place) {
            return setId(place.getId());
        }

        class NameSetter {
            public AdminIdSetter setName(@NotNull String name) {
                Builder.this.result.name = name;
                return new AdminIdSetter();
            }
        }

        class AdminIdSetter {
            ContainersSetter setAdminId(@NotNull Long adminId) {
                Builder.this.result.adminId = adminId;
                return new ContainersSetter();
            }

            ContainersSetter setAdminId(@NotNull Account admin) {
                return setAdminId(admin.getId());
            }
        }

        class ContainersSetter {
            UsersSetter setContainers(@NotEmpty Collection<ContainerDto> containers) {
                Builder.this.result.containers = containers;
                return new UsersSetter();
            }
        }

        class UsersSetter {
            FinalBuilder setUsers(@NotEmpty Collection<PlaceUserDto> users) {
                Builder.this.result.users = users;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            PlaceDto build() {
                return Builder.this.result;
            }
        }

    }

}
