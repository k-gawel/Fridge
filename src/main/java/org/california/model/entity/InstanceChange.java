package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.util.ChangeOnInstance;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
public class InstanceChange extends BaseEntity {

    @ManyToOne @JoinColumn
    private ItemInstance instance;

    @ManyToOne @JoinColumn
    private Account account;

    private LocalDate changeDate;

    private ChangeOnInstance changeType;


}

