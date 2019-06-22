package org.california.model.entity.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.entity.BaseNamedEntity;

import javax.persistence.Entity;

@Entity
@Getter @Setter @ToString
public class Allergen extends BaseNamedEntity {

    public Allergen(String name) {
        super(name);
    }

    public Allergen() {
    }

}
