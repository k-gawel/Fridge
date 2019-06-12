package org.california.model.transfer.request;

import java.io.Serializable;
import java.util.Date;

public class ItemInstanceForm implements Serializable {

    private Long itemId;
    private Long containerId;
    private Date expireDate;
    private String comment;


    public ItemInstanceForm() {}

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
