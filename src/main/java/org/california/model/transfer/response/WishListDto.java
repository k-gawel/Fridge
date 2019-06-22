package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.entity.Place;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode @ToString
public class WishListDto implements Serializable {

    private Long id;
    private Long placeId;
    private boolean status;

    private String name;
    private String description;

    private Collection<WishListItemDto> items;
    
    public static class Builder {

        private WishListDto result = new WishListDto();

        public Builder() {}

        public NameSetter setId(Long id) {
            this.result.id = id;
            return new NameSetter();
        }

        public class NameSetter {
            public DescriptionSetter setName(String name) {
                Builder.this.result.name = name;
                return new DescriptionSetter();
            }
        }

        public class DescriptionSetter {
            public PlaceIdSetter setDescription(String description) {
                Builder.this.result.description = description;
                return new PlaceIdSetter();
            }
        }

        public class PlaceIdSetter {
            public ItemsSetter setPlaceId(Long placeId) {
                Builder.this.result.placeId = placeId;
                return new ItemsSetter();
            }

            public ItemsSetter setPlaceId(@NotNull Place place) {
                return setPlaceId(place.getId());
            }
        }

        public class ItemsSetter {
            public StatusSetter setItems(Collection<WishListItemDto> items) {
                Builder.this.result.items = items;
                return new StatusSetter();
            }
        }

        public class StatusSetter {
            public FinalBuilder setStatus(boolean status) {
                Builder.this.result.status = status;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public WishListDto build() {
                return Builder.this.result;
            }
        }

    }

}

