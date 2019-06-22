package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class AllergenForm implements Serializable {

    public final String name;
    public final boolean contains;

    @JsonCreator
    public AllergenForm(String name, boolean contains) {
        this.name = name;
        this.contains = contains;
    }

}
