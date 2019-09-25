package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.WishList;
import org.california.model.entity.item.Category;
import org.california.service.serialization.EntityById;
import org.california.service.serialization.RequestDeserializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@ToString
public class WishListItemForm extends Form implements Serializable {

    @EntityById
    @NotNull
    public final WishList wishList;

    @EntityById
    @NotNull
    public final Account author;

    @EntityById
    @NotNull
    public final Category category;

    public final String comment;

    public WishListItemForm(WishList wishList, Account author, Category category, String comment) {
        this.wishList = wishList;
        this.author = author;
        this.category = category;
        this.comment = comment;
    }
}
