package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class ProducerForm implements Serializable {

    public final String name;

    public ProducerForm(String name) {
        this.name = name;
    }
}
