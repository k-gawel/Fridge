package org.california.model.entity.item;

import org.california.model.entity.BaseNamedEntity;

import javax.persistence.Entity;

@Entity
public class Producer extends BaseNamedEntity {

    public Producer(String name) {
        super(name);
    }

    public Producer() {
    }

}
