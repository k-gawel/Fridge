package org.california.model.transfer.response;

import org.california.model.entity.Place;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;

public class WishListDto implements Serializable {

    public Long id;
    public Long placeId;
    public boolean status;

    public String name;
    public String description;

    public Collection<WishListItemDto> items;

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

