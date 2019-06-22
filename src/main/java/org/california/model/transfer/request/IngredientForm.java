package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class IngredientForm implements Serializable {

    public final String name;

    @JsonCreator
    public IngredientForm(String name) {
        this.name = name;
    }

}
