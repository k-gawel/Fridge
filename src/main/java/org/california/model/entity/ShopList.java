package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.entity.utils.AccountDate;
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

    @ManyToOne
    @JoinColumn
    private Place place;
    private AccountDate created;

    private String description;
    private String shopName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "shopList")
    Collection<ItemInstance> instances = new HashSet<>();

}
