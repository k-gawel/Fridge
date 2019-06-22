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

        class NameSetter {
            DescriptionSetter setName(String name) {
                Builder.this.result.name = name;
                return new DescriptionSetter();
            }
        }

        class DescriptionSetter {
            PlaceIdSetter setDescription(String description) {
                Builder.this.result.description = description;
                return new PlaceIdSetter();
            }
        }

        class PlaceIdSetter {
            ItemsSetter setPlaceId(Long placeId) {
                Builder.this.result.placeId = placeId;
                return new ItemsSetter();
            }

            ItemsSetter setPlaceId(@NotNull Place place) {
                return setPlaceId(place.getId());
            }
        }

        class ItemsSetter {
            StatusSetter setItems(Collection<WishListItemDto> items) {
                Builder.this.result.items = items;
                return new StatusSetter();
            }
        }

        class StatusSetter {
            FinalBuilder setStatus(boolean status) {
                Builder.this.result.status = status;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            public WishListDto build() {
                return Builder.this.result;
            }
        }

    }

}

