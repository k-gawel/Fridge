package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class PlaceForm implements Serializable {

    public final String name;

    @JsonCreator
    public PlaceForm(String name) {
        this.name = name;
    }

}
