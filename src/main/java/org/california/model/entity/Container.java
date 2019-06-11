package org.california.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
public class Container extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "container")
    private transient Collection<ItemInstance> itemInstances = new HashSet<>();

    @Column(nullable = false)
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "place_id")
    private Place place;

    public Container() {
        this.date = new Date();
    }


    @Override
    public String toString() {
        return "ID: [" + id + "] name: [" + name + "] placeId: [" + place.getId() + "]";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ItemInstance> getItemsInstances() {
        return itemInstances != null ? itemInstances : new ArrayList<>();
    }

    public void setItemsInstances(Collection<ItemInstance> items) {
        this.itemInstances = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
