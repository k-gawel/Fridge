package org.california.model.entity;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Place> getPlaces() {
        if(places == null) return new HashSet<>();
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
