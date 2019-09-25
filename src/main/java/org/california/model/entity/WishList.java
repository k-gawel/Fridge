package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
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


    private LocalDate createdOn;
    private LocalDate archivedOn;


    public WishList() { }

}
