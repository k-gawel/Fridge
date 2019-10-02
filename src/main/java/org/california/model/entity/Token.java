package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseEntity {

    @OneToOne
    @JoinColumn
    private Account account;

    @ColumnDefault("0")
    private String token;

    @Column
    private Date expirationDate;

}
