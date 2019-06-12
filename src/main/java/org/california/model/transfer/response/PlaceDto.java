package org.california.model.transfer.response;

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

        public void setId(Long id) {
            result.id = id;
        }

        public void setName(String name) {
            result.name = name;
        }

        public void setAdminId(Long adminId) {
            result.adminId = adminId;
        }

        public void setContainers(Collection<ContainerDto> containers) {
            result.containers = containers;
        }

        public void setUsers(Collection<PlaceUserDto> users) {
            result.users = users;
        }

        public PlaceDto build() {
            if(result.id == null || result.name == null || result.adminId == null
                    || result.users == null || result.containers == null)
                throw new IllegalStateException("params must not be null");

            if(result.containers.isEmpty() || result.users.isEmpty())
                throw new IllegalStateException("containers and users must not be null");

            return result;
        }

    }

}
