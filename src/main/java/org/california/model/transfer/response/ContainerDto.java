package org.california.model.transfer.response;



import java.io.Serializable;

public class ContainerDto implements Serializable {

    public Long id;
    public String name;
    public Long placeId;
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerDto that = (ContainerDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return placeId != null ? placeId.equals(that.placeId) : that.placeId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContainerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", placeId=" + placeId +
                '}';
    }

    public static class Builder {

        private ContainerDto result = new ContainerDto();


        public void setId(Long id) {
            result.id = id;
        }

        public void setName(String name) {
            result.name = name;
        }

        public void setPlaceId(Long placeId) {
            result.placeId = placeId;
        }


        public ContainerDto build() {
            if(result.id == null || result.placeId == null || result.name == null)
                throw new IllegalStateException("nullable_values");
            return result;
        }

    }

}
