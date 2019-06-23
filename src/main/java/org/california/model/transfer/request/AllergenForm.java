package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllergenForm implements Serializable {

    public final String name;
    public final boolean contains;

    @JsonCreator
    public AllergenForm(String name, boolean contains) {
        this.name = name;
        this.contains = contains;
    }

}
