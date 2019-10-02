package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.california.model.entity.item.Item;
import org.california.model.entity.utils.AccountDate;
import org.joda.money.Money;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ItemInstance extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Item item;

    @ManyToOne
    private Container container;
    private LocalDate expireOn;

    private String comment;
    private Money price;

    @OneToOne @JoinColumn
    private WishListItem wishListItem;
    @ManyToOne @JoinColumn
    private ShopList shopList;


    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    private AccountDate added;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    private AccountDate opened;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    private AccountDate frozened;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    private AccountDate deleted;

    public boolean isDeleted() {
        return this.deleted != null;
    }

    public boolean isOpen() {
        return this.opened != null;
    }

    public boolean isFrozen() {
        return this.frozened != null;
    }

}
