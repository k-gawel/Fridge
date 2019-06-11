package org.california.model.transfer.response;

import java.io.Serializable;

public class PlaceUserDto implements Serializable {

    public Long id;
    public String name;
    public Boolean status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceUserDto that = (PlaceUserDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlaceUserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }


    public static class Builder {

        private PlaceUserDto result = new PlaceUserDto();

        public void setId(Long id) {
            result.id = id;
        }

        public void setName(String name) {
            result.name = name;
        }

        public void setStatus(Boolean status) {
            result.status = status;
        }

        public PlaceUserDto build() {
            if(result.id == null || result.name == null || result.status == null)
                throw new IllegalStateException("parameters can't be null");
            return result;
        }

    }

}
