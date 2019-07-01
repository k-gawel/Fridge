package org.california.model.transfer.response;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode @ToString
@Getter
public class ContainerDto implements Serializable {

    private Long id;
    private String name;
    private Long place_id;
    private Collection<ItemInstanceDto> instances;
    
    public static class Builder {

        private ContainerDto result = new ContainerDto();

        public NameSetter setId(Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Container container) {
            return setId(container.getId());
        }

        public class NameSetter {
            public PlaceIdSetter setName(String name) {
                Builder.this.result.name = name;
                return new PlaceIdSetter();
            }
        }

        public class PlaceIdSetter {
            public InstancesSetter setPlaceId(Long placeId) {
                Builder.this.result.place_id = placeId;
                return new InstancesSetter();
            }

            public InstancesSetter setPlaceId(@NotNull Place place)  {
                return setPlaceId(place.getId());
            }
        }

        public class InstancesSetter {
            public FinalBuilder setInstances(@NotNull Collection<ItemInstanceDto> instances) {
                Builder.this.result.instances = instances;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public ContainerDto build() {
                return Builder.this.result;
            }
        }

    }

}
