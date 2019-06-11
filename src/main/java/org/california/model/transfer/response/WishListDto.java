package org.california.model.transfer.response;

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

        private WishListDto result;

        public void setId(Long id) {
            result.id = id;
        }

        public void setItems(Collection<WishListItemDto> items) {
            result.items = items;
        }

        public void setName(String name) {
            result.name = name;
        }

        public void setDescription(String description) {
            result.description = description;
        }

        public void setPlaceId(Long placeId) {
            result.placeId = placeId;
        }

        public void setStatus(boolean status) { result.status = status; }


        public WishListDto build() {
            if(result.id == null || result.items == null || result.description == null
                    || result.placeId == null || result.name == null)
                throw new IllegalStateException("params are null");

            return result;
        }

    }
}
