package org.california.model.entity.item;


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

    @Override
    public String toString() {
        return  "ITEM: " + super.toString() + " " + (capacity != null ? capacity.toString() : "");
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Map<Allergen, Boolean> getAllergens() {
        return allergens;
    }

    public void setAllergens(Map<Allergen, Boolean> allergens) {
        this.allergens = allergens;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }
}
