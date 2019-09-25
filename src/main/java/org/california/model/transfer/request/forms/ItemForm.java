package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.service.serialization.EntityById;
import org.california.service.serialization.RequestDeserializer;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Validated
public class ItemForm extends Form implements Serializable {

    @NotBlank(message = "item_name.blank")
    public final String name;

    @EntityById
    @NotNull
    public final Category category;

    @EntityById
    @NotNull
    public final Place place;

    public final Long barcode;

    public final String description;
    public final String storage;

    public final Map<String, Boolean> allergens;
    public final Collection<String> ingredients;
    public final NutritionForm nutrition;

    public final String producer;

    @Pattern(regexp = "(\\d+.?\\d*(G|KG|L|ML|DAG))|", message = "capacity.wrong_format")
    public final String capacity;

    public ItemForm(String name, Category category, Place place, Long barcode, String description, String storage, Map<String, Boolean> allergens, Collection<String> ingredients, NutritionForm nutrition, String producer, String capacity) {
        this.name = name;
        this.category = category;
        this.place = place;
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
