package org.california.model.entity;

import javax.persistence.Entity;

@Entity
public class Allergen extends BaseNamedEntity {

    private boolean contains;

    public boolean isContains() {
        return contains;
    }

    public void setContains(boolean contains) {
        this.contains = contains;
    }
}
