package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
public class AllergenForm implements Serializable {

    @NotBlank(message = "allergen_name.blank")
    public final String name;
    public final boolean contains;

    @JsonCreator
    public AllergenForm(String name, boolean contains) {
        this.name = name;
        this.contains = contains;
    }

}
