package org.california.model.entity.builder;

import org.california.model.entity.Place;
import org.california.model.entity.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private Item result = new Item();

    public BarcodeSetter setName(String name) {
        ItemBuilder.this.result.setName(name);
        return new BarcodeSetter();
    }

    public class BarcodeSetter {
        public PlaceSetter setBarcode(Long barcode) {
            ItemBuilder.this.result.barcode = barcode;
            return new PlaceSetter();
        }

        public PlaceSetter noBarcode() {
            return new PlaceSetter();
        }
    }

    public class PlaceSetter {

        public ProducerSetter setPlace(Place place) {
            ItemBuilder.this.result.place = place;
            return new ProducerSetter();
        }

        public ProducerSetter defaultPlace() {
            return new ProducerSetter();
        }

    }

    public class ProducerSetter {

        public CategorySetter setProducer(Producer producer) {
            ItemBuilder.this.result.producer = producer;
            return new CategorySetter();
        }

        public CategorySetter unknownProducer() {
            return new CategorySetter();
        }

    }

    public class CategorySetter {

        public DescriptionSetter setCategory(@NotNull Category category) {
            if(!category.getChildren().isEmpty())
                throw new IllegalStateException("Category must be final");
            ItemBuilder.this.result.category = category;
            return new DescriptionSetter();
        }

    }

    public class DescriptionSetter {

        public StorageSetter setDescription(@NotNull String description) {
            ItemBuilder.this.result.description = description;
            return new StorageSetter();
        }

        public StorageSetter noDescription() {
            return new StorageSetter();
        }

    }

    public class StorageSetter {

        public NutritionSetter setStorage(@NotNull String storageRules) {
            ItemBuilder.this.result.storage = storageRules;
            return new NutritionSetter();
        }

        public NutritionSetter noStorageRules() {
            return new NutritionSetter();
        }

    }

    public class NutritionSetter {

        public IngredientSetter setNutrition(@NotNull Nutrition nutrition) {
            ItemBuilder.this.result.nutrition = nutrition;
            return new IngredientSetter();
        }

    }

    public class IngredientSetter {

        public AllergensSetter setIngredients(List<Ingredient> ingredients) {
            ItemBuilder.this.result.ingredients.addAll(ingredients);
            return new AllergensSetter();
        }

        public AllergensSetter unknownIngredients() {
            return new AllergensSetter();
        }

    }

    public class AllergensSetter {

        public CapacitySetter setAllergens(Map<Allergen, Boolean> allergens) {
            ItemBuilder.this.result.allergens.putAll(allergens);
            return new CapacitySetter();
        }

        public CapacitySetter unknownAllergens() {
            return new CapacitySetter();
        }

    }

    public class CapacitySetter {


        public FinalBuilder setCapacity(Double capacity, Capacity.Unit unit) {
            ItemBuilder.this.result.capacity.setValue(capacity);
            ItemBuilder.this.result.capacity.setUnit(unit);
            return new FinalBuilder();
        }

        public FinalBuilder setCapacity(Capacity capacity) {
            ItemBuilder.this.result.capacity = capacity;
            return new FinalBuilder();
        }

        public FinalBuilder unknownCapacity() {
            return new FinalBuilder();
        }

        private String getUnit(String string) {
            return string.trim()
                    .replaceAll("\\s", "")
                    .toUpperCase()
                    .replaceAll("[^A-Za-z]", "");
        }

        private Double getValue(String string) {
            String stringValue = string
                    .trim()
                    .replaceAll("[A-Za-z]", "")
                    .replaceAll(",", ".");
            return Double.valueOf(stringValue);
        }


        private Double gToKG(Double value) {
            return value / 1000;
        }

        private Double mlToL(Double value) {
            return value / 1000;
        }

    }

    public class FinalBuilder {

        public Item build() {
            return ItemBuilder.this.result;
        }

    }

}
