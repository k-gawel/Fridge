package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class ProducerForm implements Serializable {

    public final String name;

    public ProducerForm(String name) {
        this.name = name;
    }
}
