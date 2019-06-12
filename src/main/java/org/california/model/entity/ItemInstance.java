package org.california.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ItemInstance extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Item item;

    @ManyToOne
    @JoinColumn
    private Container container;

    private String comment;

    private Date expireOn;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Account addedBy;
    private Date addedOn;


    @ManyToOne
    @JoinColumn
    private Account openBy;
    private Date openOn;


    @ManyToOne
    @JoinColumn
    private Account frozenBy;
    private Date frozenOn;
    private boolean frozen;

    @ManyToOne
    @JoinColumn
    private Account deletedBy;
    private Date deletedOn;


    @Override
    public String toString() {
        return "ID: [" + id + "] itemId: [" + item.getId() + "] containerId: [" + container.getId() + "]";
    }


    public ItemInstance() {}

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public Date getOpenOn() {
        return openOn;
    }

    public void setOpenOn(Date openOn) {
        this.openOn = openOn;
    }

    public Date getFrozenOn() {
        return frozenOn;
    }

    public void setFrozenOn(Date frozenOn) {
        this.frozenOn = frozenOn;
    }

    public Date getExpireOn() {
        return expireOn;
    }

    public void setExpireOn(Date expireOn) {
        this.expireOn = expireOn;
    }

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }

    public Account getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Account addedBy) {
        this.addedBy = addedBy;
    }

    public Account getOpenBy() {
        return openBy;
    }

    public void setOpenBy(Account openBy) {
        this.openBy = openBy;
    }

    public Account getFrozenBy() {
        return frozenBy;
    }

    public void setFrozenBy(Account frozenBy) {
        this.frozenBy = frozenBy;
    }

    public Account getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Account deletedBy) {
        this.deletedBy = deletedBy;
    }
}
