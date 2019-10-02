package org.california.model.entity.item;

import lombok.Getter;
import lombok.Setter;
import org.california.model.entity.BaseNamedEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Producer extends BaseNamedEntity {

    public Producer(String name) {
        super(name);
    }

    public Producer() {
    }

}
