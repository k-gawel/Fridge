package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemForm implements Serializable {

    public final String name;
    public final Long categoryId;
    public final Long placeId;

    public final long barcode;
    public final String description;
    public final String storage;

    public final Map<String, Boolean> allergens;
    public final Collection<String> ingredients;
    public final NutritionForm nutrition;

    public final String producer;
    public final String capacity;


    @JsonCreator
    public ItemForm(String name, Long categoryId, Long placeId, long barcode,
                    String description, String storage, Map<String, Boolean> allergens,
                    Collection<String> ingredients, NutritionForm nutrition,
                    String producer, String capacity) {
        this.name = name;
        this.categoryId = categoryId;
        this.placeId = placeId;
        this.barcode = barcode;
        this.description = description;
        this.storage = storage;
        this.allergens = allergens;
        this.ingredients = ingredients;
        this.nutrition = nutrition;
        this.producer = producer;
        this.capacity = capacity;
    }
}
