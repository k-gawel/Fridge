package org.california.model.transfer.request.queries;

public class ItemInstanceParams {

    public Boolean opened;
    public Boolean frozen;
    public Boolean deleted;

    protected ItemInstanceParams() {
    }

    public ItemInstanceParams(Boolean opened, Boolean frozen, Boolean deleted) {
        this.opened = opened;
        this.frozen = frozen;
        this.deleted = deleted;
    }

}
