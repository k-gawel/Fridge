package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.california.model.entity.Container;

import java.io.Serializable;

public class ContainerForm implements Serializable {

    public final Long placeId;
    public final String name;

    @JsonCreator
    public ContainerForm(Long placeId, String name) {
        this.placeId = placeId;
        this.name = name;
    }


    public boolean validate() {
        if (placeId == null || name == null) return false;
        if (name.equals("")) return false;
        return true;
    }

}
