package org.california.model.transfer.request.forms;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ShopList;
import org.california.model.entity.WishListItem;
import org.california.model.entity.item.Item;
import org.california.service.serialization.annotations.ById;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Validated
public class ItemInstanceForm extends Form implements Serializable {

    @ById
    @NotNull(message = "user.null")
    public final Account user;

    @ById
    @NotNull(message = "item.null")
    public final Item item;

    @ById
    @NotNull(message = "container.null")
    public final Container container;

    @ById
    public final ShopList shopList;

    @ById
    public final WishListItem wishListItem;

    @FutureOrPresent(message = "expireDate.past")
    public final Date expireDate;

    public final MoneyForm price;

    public final String comment;

    public ItemInstanceForm(Account user, Item item, Container container, ShopList shopList, WishListItem wishListItem, Date expireDate, MoneyForm price, String comment) {
        this.user = user;
        this.item = item;
        this.container = container;
        this.shopList = shopList;
        this.wishListItem = wishListItem;
        this.expireDate = expireDate;
        this.price = price;
        this.comment = comment;
    }

}
