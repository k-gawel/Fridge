package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.service.serialization.EntityById;
import org.california.service.serialization.RequestDeserializer;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
public class WishListForm extends Form implements Serializable {

    @NotBlank(message = "name.blank")
    public final String name;

    @NotNull
    @EntityById
    public final Place place;

    @NotNull(message = "description.null")
    public final String description;

    @EntityById
    @NotNull
    public final Account author;

    public WishListForm(String name, Place place, String description, Account author) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.author = author;
    }
}
