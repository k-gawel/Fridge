package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class AllergenForm extends Form implements Serializable {

    @NotBlank(message = "allergen_name.blank")
    public final String name;
    public final boolean contains;

    public AllergenForm(String name, boolean contains) {
        this.name = name;
        this.contains = contains;
    }

}
