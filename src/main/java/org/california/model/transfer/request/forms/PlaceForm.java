package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class PlaceForm extends Form implements Serializable {

    @NotBlank(message = "name.blank")
    public final String name;

    public PlaceForm(String name) {
        this.name = name;
    }

}
