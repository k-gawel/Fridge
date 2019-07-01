package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.transfer.request.validator.EntityExists;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@EqualsAndHashCode @ToString
public class WishListItemForm implements Serializable {

    @EntityExists(entityType = EntityExists.EntityType.WishList)
    public final Long wish_list_id;

    @EntityExists(entityType = EntityExists.EntityType.Account)
    public final Long author_id;

    @EntityExists(entityType = EntityExists.EntityType.Category)
    public final Long category_id;

    public final String comment;

    @JsonCreator
    public WishListItemForm(Long category_id, String comment, Long wish_list_id, Long author_id) {
        this.category_id = category_id;
        this.comment = comment;
        this.wish_list_id = wish_list_id;
        this.author_id = author_id;
    }
}
