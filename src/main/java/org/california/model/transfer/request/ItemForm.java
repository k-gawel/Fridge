package org.california.model.transfer.request;

import java.io.Serializable;
import java.util.Collection;


public class ItemForm implements Serializable {


    private String name;
    private Long categoryId;
    private Long placeId;

    private long barcode;
    private String description;
    private String storage;

    private Collection<AllergenForm> allergens;
    private Collection<IngredientForm> ingredients;
    private NutritionForm nutrition;

    private ProducerForm producer;
    private Double netWeight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
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

    public Collection<AllergenForm> getAllergens() {
        return allergens;
    }

    public void setAllergens(Collection<AllergenForm> allergens) {
        this.allergens = allergens;
    }

    public Collection<IngredientForm> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<IngredientForm> ingredients) {
        this.ingredients = ingredients;
    }

    public NutritionForm getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionForm nutrition) {
        this.nutrition = nutrition;
    }

    public ProducerForm getProducer() {
        return producer;
    }

    public void setProducer(ProducerForm producer) {
        this.producer = producer;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }
}
