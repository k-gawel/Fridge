package org.california.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
public class Place extends BaseEntity {


    @Column(nullable = false)
    private String name;


    @LazyCollection(value = LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place")
    private Collection<Container> containers = new HashSet<>();


    @OneToOne
    @JoinColumn(nullable = false, name = "admin_id")
    private Account admin;


    @LazyCollection(value = LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(joinColumns        = @JoinColumn(name = "place_id"),
               inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts = new HashSet<>();


    @LazyCollection(value              = LazyCollectionOption.FALSE)
    @ManyToMany(    cascade            = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(     joinColumns        = @JoinColumn(name = "place_id"),
                    inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> unaactiveAccounts = new HashSet<>();


    private Date createdOn = new Date();


    @Override
    public String toString() {
        return "ID: [" + id + "] name: [" + name + "]";
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Collection<Container> getContainers() {
        if (containers == null) containers = Collections.emptySet();
        return containers;
    }


    public void setContainers(Collection<Container> containers) {
        this.containers = containers;
    }


    public Set<Account> getAccounts() {
        if (accounts == null) accounts = new HashSet<>();
        return accounts;
    }


    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }


    public Set<Account> getUnaactiveAccounts() {
        if (unaactiveAccounts == null) unaactiveAccounts = new HashSet<>();
        return unaactiveAccounts;
    }


    public void setUnaactiveAccounts(Set<Account> unactiveAccounts) {
        this.unaactiveAccounts = unactiveAccounts;
    }


    public Account getAdmin() {
        return admin;
    }


    public void setAdmin(Account admin) {
        this.admin = admin;
    }


    public Date getCreatedOn() {
        return createdOn;
    }


    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
