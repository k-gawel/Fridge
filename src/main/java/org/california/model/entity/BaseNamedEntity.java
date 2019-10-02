package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class BaseNamedEntity extends BaseEntity {

    protected String name;

    public BaseNamedEntity() {
    }

    public BaseNamedEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ " + getClass().getSimpleName() + ", ID: " + id + " , NAME: " + name + " ]";
    }
}
