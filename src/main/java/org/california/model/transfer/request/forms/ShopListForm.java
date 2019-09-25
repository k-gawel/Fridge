package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.california.model.entity.Place;
import org.california.service.serialization.EntityById;
import org.california.service.serialization.RequestDeserializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;

@Validated
public class ShopListForm extends Form implements Serializable {

    public final String shopName;
    public final LocalDate createdOn;
    public final String description;

    @EntityById
    @NotNull
    public final Place place;

    public ShopListForm(String shopName, LocalDate createdOn, String description, Place place) {
        this.shopName = shopName;
        this.createdOn = createdOn;
        this.description = description;
        this.place = place;
    }

}
