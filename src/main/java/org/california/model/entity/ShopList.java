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
@Setter
@Getter
public class ShopList extends BaseEntity {

    boolean status = true;

    private LocalDate createdOn;
    private String description;
    private String shopName;

    @ManyToOne
    @JoinColumn
    private Place place;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "shopList")
    Collection<ItemInstance> instances = new HashSet<>();


}
