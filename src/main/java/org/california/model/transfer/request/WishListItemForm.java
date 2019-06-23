package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode @ToString
public class WishListItemForm implements Serializable {

    public final Long wish_list_id;
    public final Long author_id;
    public final Long category_id;
    public final Long item_id;
    public final String comment;

    @JsonCreator
    public WishListItemForm(Long category_id, Long item_id, String comment, Long wish_list_id, Long author_id) {
        this.category_id = category_id;
        this.item_id = item_id;
        this.comment = comment;
        this.wish_list_id = wish_list_id;
        this.author_id = author_id;
    }
}
