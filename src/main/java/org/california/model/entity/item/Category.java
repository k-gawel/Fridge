package org.california.model.entity.item;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.entity.BaseNamedEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;


@Entity
@Getter
@Setter
public class Category extends BaseNamedEntity {

    @ManyToOne @JoinColumn
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category parent;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "parent")
    @ToString.Exclude
    private Collection<Category> children = new HashSet<>();

    public Category() { }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

}

