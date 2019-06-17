package org.california.model.transfer.response;



import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.jetbrains.annotations.NotNull;

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

        public NameSetter setId(Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Container container) {
            return setId(container.getId());
        }

        class NameSetter {
            PlaceIdSetter setName(String name) {
                Builder.this.result.name = name;
                return new PlaceIdSetter();
            }
        }

        class PlaceIdSetter {
            FinalBuilder setPlaceId(Long placeId) {
                Builder.this.result.placeId = placeId;
                return new FinalBuilder();
            }

            FinalBuilder setPlaceId(@NotNull Place place)  {
                return setPlaceId(place.getId());
            }
        }

        class FinalBuilder {
            ContainerDto build() {
                return Builder.this.result;
            }
        }

    }

}
