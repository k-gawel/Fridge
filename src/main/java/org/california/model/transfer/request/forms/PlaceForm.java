package org.california.model.transfer.request.forms;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Validated
public class PlaceForm extends Form implements Serializable {

    @Size(min = 5, max = 30, message = "name.length")
    public final String name;

    public PlaceForm(String name) {
        this.name = name;
    }

}
