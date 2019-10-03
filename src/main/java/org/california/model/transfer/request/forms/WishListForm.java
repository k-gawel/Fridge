package org.california.model.transfer.request.forms;

import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.service.serialization.annotations.ById;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Validated
public class WishListForm extends Form implements Serializable {

    @Size(min = 5, max = 30, message = "name.length")
    public final String name;

    @ById
    @NotNull(message = "place.null")
    public final Place place;

    public final String description;

    @ById
    @NotNull(message = "user.null")
    public final Account author;

    public WishListForm(String name, Place place, String description, Account author) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.author = author;
    }
}
