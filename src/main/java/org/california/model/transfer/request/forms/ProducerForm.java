package org.california.model.transfer.request.forms;

import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
public class ProducerForm extends Form implements Serializable {

    public final String name;

    public ProducerForm(String name) {
        this.name = name;
    }

}
