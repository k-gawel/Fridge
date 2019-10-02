package org.california.model.transfer.request.forms;

import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.service.serialization.ById;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
public class ShopListForm extends Form implements Serializable {

    @ById
    @NotNull(message = "place.null")
    public final Place place;

    @ById
    @NotNull(message = "user.null")
    public final Account author;

    public final String shopName;
    public final String description;

    public ShopListForm(Place place, Account author, String shopName, String description) {
        this.place = place;
        this.author = author;
        this.shopName = shopName;
        this.description = description;
    }
}
