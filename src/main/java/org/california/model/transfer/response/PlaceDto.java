package org.california.model.transfer.response;

import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;

public class PlaceDto implements Serializable {

    public Long id;
    public String name;
    public Long adminId;

    public Collection<ContainerDto> containers;
    public Collection<PlaceUserDto> users;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceDto placeDto = (PlaceDto) o;

        if (id != null ? !id.equals(placeDto.id) : placeDto.id != null) return false;
        if (name != null ? !name.equals(placeDto.name) : placeDto.name != null) return false;
        if (adminId != null ? !adminId.equals(placeDto.adminId) : placeDto.adminId != null) return false;
        if (containers != null ? !containers.equals(placeDto.containers) : placeDto.containers != null) return false;
        return users != null ? users.equals(placeDto.users) : placeDto.users == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (adminId != null ? adminId.hashCode() : 0);
        result = 31 * result + (containers != null ? containers.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlaceDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adminId=" + adminId +
                ", containers=" + containers +
                ", users=" + users +
                '}';
    }


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
