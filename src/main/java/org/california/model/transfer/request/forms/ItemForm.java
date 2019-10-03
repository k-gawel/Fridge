package org.california.model.transfer.request.forms;

import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.service.serialization.annotations.ById;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Validated
public class ItemForm extends Form implements Serializable {

    @Size(min = 5, max = 255, message = "name.length")
    public final String name;

    @ById
    @NotNull(message = "category.null")
    public final Category category;

    @ById
    @NotNull(message = "place.null")
    public final Place place;

    public final Long barcode;

    @Size(min = 5, message = "description.length")
    public final String description;
    public final String storage;

    public final Map<String, Boolean> allergens;
    public final Collection<String> ingredients;
    public final NutritionForm nutrition;

    public final String producer;

    @Pattern(regexp = "(\\d+.?\\d*(G|KG|L|ML|DAG))|", message = "capacity.wrongFormat")
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
