package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.california.model.entity.item.Category;
import org.california.model.entity.utils.AccountDate;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class WishListItem extends BaseEntity {

    @ManyToOne
    private WishList wishList;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    AccountDate created;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    AccountDate added;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "wishListItem") @JoinColumn
    private ItemInstance addedInstance;

    private String comment;

    @ManyToOne @JoinColumn
    private Category category;

}
