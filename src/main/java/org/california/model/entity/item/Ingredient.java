package org.california.model.entity.item;

import org.california.model.entity.BaseNamedEntity;

import javax.persistence.Entity;

@Entity
public class Ingredient extends BaseNamedEntity {

    public Ingredient(String name) {
        super(name);
    }

    public Ingredient() {
    }

}
