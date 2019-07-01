package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.california.model.transfer.request.validator.EntityExists;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Validated
public class ItemForm implements Serializable {

    @NotBlank(message = "item_name.blank")
    public final String name;

    @EntityExists(entityType = EntityExists.EntityType.Category)
    public final Long categoryId;

    @EntityExists(entityType = EntityExists.EntityType.Place)
    public final Long placeId;

    public final Long barcode;

    public final String description;
    public final String storage;

    public final Map<String, Boolean> allergens;
    public final Collection<String> ingredients;
    public final NutritionForm nutrition;

    public final String producer;

    @Pattern(regexp = "(\\d+.?\\d*(G|KG|L|ML|DAG))|", message = "capacity.wrong_format")
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
