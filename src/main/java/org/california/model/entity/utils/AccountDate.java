package org.california.model.entity.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class AccountDate extends BaseEntity {

    @ManyToOne
    private Account account;
    private LocalDate date;

    public AccountDate() {
    }

    public AccountDate(Account account) {
        this.account = account;
        this.date = LocalDate.now();
    }

    public AccountDate(Account account, LocalDate date) {
        this.account = account;
        this.date = date;
    }

}
