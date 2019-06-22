package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.california.model.util.ChangeOnInstance;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter @Setter
public class InstanceChange extends BaseEntity {

    @ManyToOne @JoinColumn
    private ItemInstance instance;

    @ManyToOne @JoinColumn
    private Account account;

    private Date changeDate;

    private ChangeOnInstance changeType;


    public ItemInstance getInstance() {
        return instance;
    }

    public void setInstance(ItemInstance instance) {
        this.instance = instance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date date) {
        this.changeDate = date;
    }

    public ChangeOnInstance getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeOnInstance change) {
        this.changeType = change;
    }

    public String toString() {

        return account.getName() + " " + changeType.toString() + " " + instance.getId() + " on " + changeDate.toString();

    }

}

