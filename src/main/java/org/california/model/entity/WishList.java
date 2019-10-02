package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.california.model.entity.utils.AccountDate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
public class WishList extends BaseNamedEntity {

    private String description;
    private boolean status = true;

    @ManyToOne @JoinColumn
    private Place place;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "wishList")
    private Collection<WishListItem> items = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    private AccountDate created;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    private AccountDate archived;


    public WishList() { }

}
