package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter @ToString
public class BaseNamedEntity extends BaseEntity {

    protected String name;

    public BaseNamedEntity() {
    }

    public BaseNamedEntity(String name) {
        this.name = name;
    }

}
