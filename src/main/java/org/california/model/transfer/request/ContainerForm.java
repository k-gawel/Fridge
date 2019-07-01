package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.california.model.transfer.request.validator.EntityExists;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class ContainerForm implements Serializable {

    @EntityExists(entityType = EntityExists.EntityType.Place)
    public final Long placeId;

    @NotBlank(message = "name.blank")
    public final String name;

    @JsonCreator
    public ContainerForm(Long placeId, String name) {
        this.placeId = placeId;
        this.name = name;
    }

}
