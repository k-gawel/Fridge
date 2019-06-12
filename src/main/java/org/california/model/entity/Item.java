package org.california.model.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item extends BaseNamedEntity {

    @Column(length = 100000)
    private Long barcode;

    @ManyToOne @JoinColumn
    Place place;

    @ManyToOne(optional = false) @JoinColumn
    private Category category;

    @Column(length = 100000)
    private String description;


    @Column(length = 100000)
    private String storage;

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns        = @JoinColumn(name = "item_id"),
               inverseJoinColumns = @JoinColumn(name = "allergen_id"))
    private List<Allergen> allergens;

    @OneToOne(cascade = CascadeType.ALL)
    private Nutrition nutrition;

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns        = @JoinColumn(name = "item_id"),
               inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;


    private double netWeight;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Producent producent;


    @Override
    public String toString() {
        return  "ID: [" + id + "] " +
                "name: [" + name + "]";
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

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public Producent getProducent() {
        return producent;
    }

    public void setProducent(Producent producent) {
        this.producent = producent;
    }
}
