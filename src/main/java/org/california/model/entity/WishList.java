package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter @Setter
public class WishList extends BaseNamedEntity {

    private String description;
    private boolean status = true;

    @ManyToOne @JoinColumn
    private Place place;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "wishList")
    private Collection<WishListItem> items = new HashSet<>();

    public WishList() { }

    public WishList(WishList wishList) {
        this.id = wishList.getId();
        this.name = wishList.getName();
        this.description = wishList.getDescription();
        this.status = wishList.isStatus();

        this.place = wishList.getPlace();
        this.items = wishList.getItems();
    }

}
