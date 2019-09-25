package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.entity.item.Category;
import org.california.model.entity.utils.AccountDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class WishListItem extends BaseEntity {

    private Date createdOn;

    @ManyToOne @JoinColumn
    private Account author;

    @ManyToOne
    private WishList wishList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    AccountDate added;

    @ManyToOne @JoinColumn
    private ItemInstance addedInstance;


    private String comment;


    @ManyToOne @JoinColumn
    private Category category;

}
