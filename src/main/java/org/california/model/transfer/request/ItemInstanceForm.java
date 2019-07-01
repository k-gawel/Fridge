package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.california.model.transfer.request.validator.EntityExists;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.FutureOrPresent;
import java.io.Serializable;
import java.util.Date;

@Validated
public class ItemInstanceForm implements Serializable {

    @EntityExists(entityType = EntityExists.EntityType.Item)
    public final Long itemId;

    @EntityExists(entityType = EntityExists.EntityType.Container)
    public final Long containerId;

    @FutureOrPresent(message = "expire_date.past")
    public final Date expireDate;

    public final String comment;

    @JsonCreator
    public ItemInstanceForm(Long itemId, Long containerId, Date expireDate, String comment) {
        this.itemId = itemId;
        this.containerId = containerId;
        this.expireDate = expireDate;
        this.comment = comment;
    }
}
