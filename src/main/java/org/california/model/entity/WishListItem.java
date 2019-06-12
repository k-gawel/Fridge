package org.california.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class WishListItem extends BaseEntity {

    private Date createdOn;
    @ManyToOne @JoinColumn
    private Account author;
    @ManyToOne
    private WishList wishList;


    private Date addedOn;
    @ManyToOne @JoinColumn
    private ItemInstance addedInstance;
    @ManyToOne @JoinColumn
    private Account addedBy;


    private String comment;
    @ManyToOne @JoinColumn
    private Category category;
    @ManyToOne @JoinColumn
    private Item item;


    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Account getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Account addedBy) {
        this.addedBy = addedBy;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public ItemInstance getAddedInstance() {
        return addedInstance;
    }

    public void setAddedInstance(ItemInstance addedInstance) {
        this.addedInstance = addedInstance;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

}
