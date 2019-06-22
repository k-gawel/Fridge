package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.entity.item.Item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
public class ItemInstance extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Item item;

    @ManyToOne @JoinColumn
    private Container container;

    private String comment;

    private LocalDate expireOn;

    @ManyToOne @JoinColumn
    private Account addedBy;
    private LocalDate addedOn;


    @ManyToOne @JoinColumn
    private Account openBy;
    private LocalDate openOn;


    @ManyToOne @JoinColumn
    private Account frozenBy;
    private LocalDate frozenOn;
    private boolean frozen;

    @ManyToOne @JoinColumn
    private Account deletedBy;
    private LocalDate deletedOn;

}
