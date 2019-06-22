package org.california.model.transfer.response;



import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@EqualsAndHashCode @ToString
public class ContainerDto implements Serializable {

    private Long id;
    private String name;
    private Long placeId;
    
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
