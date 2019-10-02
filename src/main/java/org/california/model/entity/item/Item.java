package org.california.model.entity.item;


import lombok.Getter;
import lombok.Setter;
import org.california.model.entity.BaseNamedEntity;
import org.california.model.entity.Place;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class Item extends BaseNamedEntity {

    @Column(length = 100000)
    public Long barcode;

    @ManyToOne @JoinColumn
    public Place place;

    @ManyToOne(optional = false) @JoinColumn
    public Category category;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn
    public Producer producer;

    @Column(length = 100000)
    public String description;

    @Column(length = 100000)
    public String storage;

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "Item_Allergen", joinColumns = @JoinColumn(name = "item_id"))
    @MapKeyJoinColumn(name = "allergen_id")
    public Map<Allergen, Boolean> allergens = new HashMap<>();

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(joinColumns         = @JoinColumn(name = "item_id"),
                inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    public Set<Ingredient> ingredients = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    public Nutrition nutrition;

    @OneToOne(cascade = CascadeType.ALL)
    public Capacity capacity;


}
