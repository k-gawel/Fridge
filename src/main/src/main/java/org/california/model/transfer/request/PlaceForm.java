package org.california.model.transfer.request;

import java.io.Serializable;

public class PlaceForm implements Serializable {

    private String name;

    public PlaceForm() {}

    public PlaceForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
