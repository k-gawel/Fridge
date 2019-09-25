package org.california.repository.iteminstance;

public class Parameters {

    public final Boolean opened;
    public final Boolean deleted;
    public final Boolean frozen;

    public Parameters(Boolean deleted, Boolean opened, Boolean frozen) {
        this.deleted = deleted;
        this.opened = opened;
        this.frozen = frozen;
    }

}
