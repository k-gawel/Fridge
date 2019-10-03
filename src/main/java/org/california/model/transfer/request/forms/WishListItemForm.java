package org.california.model.transfer.request.forms;

import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.WishList;
import org.california.model.entity.item.Category;
import org.california.service.serialization.annotations.ById;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Validated
@ToString
public class WishListItemForm extends Form implements Serializable {

    @ById
    @NotNull("wishList.null")
    public final WishList wishList;

    @ById
    @NotNull("user.null")
    public final Account author;

    @ById
    @NotNull("category.null")
    public final Category category;

    @Size(max = 255, message = "comment.length")
    public final String comment;

    public WishListItemForm(WishList wishList, Account author, Category category, String comment) {
        this.wishList = wishList;
        this.author = author;
        this.category = category;
        this.comment = comment;
    }
}
