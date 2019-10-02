package org.california.model.entity.item;

import lombok.Getter;
import lombok.Setter;
import org.california.model.entity.BaseNamedEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Ingredient extends BaseNamedEntity {

    public Ingredient(String name) {
        super(name);
    }

    public Ingredient() {
    }

}
