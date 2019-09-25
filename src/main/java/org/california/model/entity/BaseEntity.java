package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

    @Column
    protected String uuid = UUID.randomUUID().toString();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "[ " + getClass().getSimpleName() + ", ID: " + id + " ]";
    }
}
