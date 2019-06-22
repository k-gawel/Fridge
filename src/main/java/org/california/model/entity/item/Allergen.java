package org.california.model.entity.item;

import org.california.model.entity.BaseNamedEntity;

import javax.persistence.Entity;

@Entity
public class Allergen extends BaseNamedEntity {

    public Allergen(String name) {
        super(name);
    }

    public Allergen() {
    }

}
