package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@Getter
@Setter
public class Container extends BaseNamedEntity {

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "container")
    private Collection<ItemInstance> itemInstances = new HashSet<>();

    @Column(nullable = false)
    private Date date = new Date();

    @ManyToOne(optional = false)
    @JoinColumn(name = "place_id")
    private Place place;

    public Container() {
    }


}
