package org.california.model.transfer.request.forms;

import org.california.model.entity.Place;
import org.california.service.serialization.ById;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Validated
public class ContainerForm extends Form implements Serializable {

    @ById
    @NotNull
    public final Place place;

    @Size(min = 5, max = 30, message = "name.length")
    public final String name;

    public ContainerForm(Place place, String name) {
        this.place = place;
        this.name = name;
    }

}
