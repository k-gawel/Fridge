package org.california.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Entity
public class Category extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category parent;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "parent")
    private Collection<Category> children = new HashSet<>();

    @Override
    public String toString() {
        return "ID: [" + id + " ] name: [" + name + " ] final: [" + children.isEmpty() + " ]";
    }

    public Category() { }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;

    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Collection<Category> getChildren() {
        if(children == null) children = Collections.emptySet();
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

}

