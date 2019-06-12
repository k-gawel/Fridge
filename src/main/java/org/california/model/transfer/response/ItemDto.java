package org.california.model.transfer.response;

import java.io.Serializable;
import java.util.Collection;

public class ItemDto implements Serializable {

    public Long id;
    public String name;
    public Long barcode;

    public Long placeId;
    public Long categoryId;
    public ProducerDto producer;

    public String description;
    public String storage;

    public Collection<AllergenDto> allergens;
    public Collection<IngredientDto> ingredients;
    public NutritionDto nutrition;


    public static class Builder {
        
        private ItemDto result = new ItemDto();
        
        public Builder setId(Long id) {
            result.id = id;
             return this;
        }

        public Builder setName(String name) {
            result.name = name;
            return this;
        }

        public Builder setBarcode(Long barcode) {
            result.barcode = barcode;
            return this;
        }

        public Builder setPlaceId(Long placeId) {
            result.placeId = placeId;
            return this;
        }

        public Builder setCategoryId(Long categoryId) {
            result.categoryId = categoryId;
            return this;
        }

        public Builder setProducer(ProducerDto producer) {
            result.producer = producer;
            return this;
        }

        public Builder setDescription(String description) {
            result.description = description;
            return this;
        }

        public Builder setStorage(String storage) {
            result.storage = storage;
            return this;
        }

        public Builder setAllergens(Collection<AllergenDto> allergens) {
            result.allergens = allergens;
            return this;
        }

        public Builder setIngredients(Collection<IngredientDto> ingredients) {
            result.ingredients = ingredients;
            return this;
        }

        public Builder setNutrition(NutritionDto nutrition) {
            result.nutrition = nutrition;
            return this;
        }

        
        public ItemDto build() {
            if(result.id == null)
                throw new IllegalStateException("id");
            if(result.name == null)
                throw new IllegalStateException("name");
            if(result.categoryId == null)
                throw new IllegalStateException("category_id");
            return result;
        }
        
    }
}
