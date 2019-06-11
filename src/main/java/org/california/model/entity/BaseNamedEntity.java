package org.california.model.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseNamedEntity extends BaseEntity {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseNamedEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

}
