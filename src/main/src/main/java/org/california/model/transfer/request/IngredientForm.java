package org.california.model.transfer.request;

import java.io.Serializable;

public class IngredientForm implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
