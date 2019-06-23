package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;


public class ItemInstanceForm implements Serializable {

    public final Long itemId;
    public final Long containerId;
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
