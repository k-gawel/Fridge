package org.california.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter @Setter @ToString
public class ItemInstance extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Item item;

    @ManyToOne @JoinColumn
    private Container container;

    private String comment;

    private Date expireOn;

    @ManyToOne @JoinColumn
    private Account addedBy;
    private Date addedOn;


    @ManyToOne @JoinColumn
    private Account openBy;
    private Date openOn;


    @ManyToOne @JoinColumn
    private Account frozenBy;
    private Date frozenOn;
    private boolean frozen;

    @ManyToOne @JoinColumn
    private Account deletedBy;
    private LocalDate deletedOn;

}
