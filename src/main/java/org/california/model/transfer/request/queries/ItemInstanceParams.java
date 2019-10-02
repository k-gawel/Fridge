package org.california.model.transfer.request.queries;

public class ItemInstanceParams {

    public final Boolean opened;
    public final Boolean frozen;
    public final Boolean deleted;

    public ItemInstanceParams(Boolean opened, Boolean frozen, Boolean deleted) {
        this.opened = opened;
        this.frozen = frozen;
        this.deleted = deleted;
    }

}
