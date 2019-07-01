package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.california.model.transfer.request.validator.EntityExists;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Validated
public class WishListForm implements Serializable {

    @NotBlank(message = "name.blank")
    public final String name;

    @EntityExists(entityType = EntityExists.EntityType.Place)
    public final long place;

    @NotNull(message = "description.null")
    public final String description;

    @EntityExists(entityType = EntityExists.EntityType.Account)
    public final long author;

    @JsonCreator
    public WishListForm(String name, long place, String description, long author) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.author = author;
    }
}
