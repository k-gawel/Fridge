package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.california.model.entity.utils.AccountDate;
import org.california.model.util.ChangeOnInstance;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class InstanceChange extends BaseEntity {

    @ManyToOne @JoinColumn
    private ItemInstance instance;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn
    private AccountDate changed;

    private ChangeOnInstance changeType;

}

