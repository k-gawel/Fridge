package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.california.model.entity.Place;
import org.california.service.serialization.EntityById;
import org.california.service.serialization.RequestDeserializer;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
public class ContainerForm extends Form implements Serializable {

    @EntityById
    @NotNull
    public final Place place;

    @NotBlank(message = "name.blank")
    public final String name;

    public ContainerForm(Place place, String name) {
        this.place = place;
        this.name = name;
    }

}
