package org.california.model.transfer.request.forms;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class IngredientForm extends Form implements Serializable {

    @NotBlank(message = "ingredient_name.blank")
    public final String name;

    public IngredientForm(String name) {
        this.name = name;
    }

}
