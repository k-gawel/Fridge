package org.california.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Account extends BaseNamedEntity {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "accounts")
    private Collection<Place> places = new HashSet<>();

    @Column(nullable = false)
    private Date createdOn = new Date();


    @Override
    public String toString() {
        return "ID: [" + id + " ] name: [" + name + " ]";
    }


}
