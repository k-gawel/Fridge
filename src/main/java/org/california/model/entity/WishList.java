package org.california.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class WishList extends BaseNamedEntity {

    private String description;
    private boolean status = true;

    @ManyToOne
    @JoinColumn
    private Place place;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "wishList")
    private Collection<WishListItem> items = new HashSet<>();

    public WishList() { }

    public WishList(WishList wishList) {
        this.id = wishList.getId();
        this.name = wishList.getName();
        this.description = wishList.getDescription();
        this.status = wishList.getStatus();

        this.place = wishList.getPlace();
        this.items = wishList.getItems();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Collection<WishListItem> getItems() {
        return items;
    }

    public void setItems(Collection<WishListItem> items) {
        this.items = items;
    }
}
