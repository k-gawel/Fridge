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
@Setter
@Getter
public class ShopList extends BaseEntity {

    boolean status = true;

    @ManyToOne @JoinColumn
    private Place place;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    private AccountDate created;

    private String description;
    private String shopName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "shopList", cascade = CascadeType.ALL)
    Collection<ItemInstance> instances = new HashSet<>();

}
