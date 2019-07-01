package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class PlaceForm implements Serializable {

    @NotBlank(message = "name.blank")
    public final String name;

    @JsonCreator
    public PlaceForm(String name) {
        this.name = name;
    }

}
