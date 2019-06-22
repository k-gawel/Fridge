package org.california.model.transfer.response;

import org.california.model.entity.Place;
import org.california.model.entity.item.Capacity;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
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
    public String capacity;

    public Collection<AllergenDto> allergens;
    public Collection<IngredientDto> ingredients;
    public NutritionDto nutrition;


    public static class Builder {
        
        private ItemDto result = new ItemDto();

        public NameSetter setId(@NotNull Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Item item) {
            return setId(item.getId());
        }

        class NameSetter {
            public BarcodeSetter setName(@NotEmpty String name) {
                Builder.this.result.name = name;
                return new BarcodeSetter();
            }

            public BarcodeSetter setName(@NotNull Item item) {
                return setName(item.getName());
            }
        }

        class BarcodeSetter {
            PlaceIdSetter setBarcode(Long barcode) {
                Builder.this.result.barcode = barcode;
                return new PlaceIdSetter();
            }

            PlaceIdSetter setBarcode(@NotNull Item item) {
                return setBarcode(item.getBarcode());
            }
        }

        class PlaceIdSetter {
            private CategoryIdSetter setPlaceId(Long placeId) {
                Builder.this.result.placeId = placeId;
                return new CategoryIdSetter();
            }

            CategoryIdSetter setPlaceId(Place place) {
                return setPlaceId(place != null ? place.getId() : null);
            }
        }

        class CategoryIdSetter {
            ProducerSetter setCategoryId(@NotNull Long categoryId) {
                Builder.this.result.categoryId = categoryId;
                return new ProducerSetter();
            }
            ProducerSetter setCategoryId(@NotNull Category category) {
                return setCategoryId(category.getId());
            }
        }

        class ProducerSetter {
            DescriptionSetter setProducer(ProducerDto producerDto) {
                Builder.this.result.producer = producerDto;
                return new DescriptionSetter();
            }
        }

        class DescriptionSetter {
            StorageSetter setDescription(String description) {
                Builder.this.result.description = description;
                return new StorageSetter();
            }

            StorageSetter setDescription(Item item) {
                return setDescription(item.getDescription());
            }
        }

        class StorageSetter {
            CapacitySetter setStorage(String storage) {
                Builder.this.result.storage = storage;
                return new CapacitySetter();
            }

            CapacitySetter setStorage(@NotNull Item item) {
                return setStorage(item.getStorage());
            }
        }

        class CapacitySetter {

            AllergensSetter setCapacity(Capacity capacity) {
                String capacityString = capacity != null ? capacity.toString() : null;
                Builder.this.result.capacity = capacityString;
                return new AllergensSetter();
            }

            AllergensSetter setCapacity(Item item) {
                return setCapacity(item.getCapacity());
            }

        }

        class AllergensSetter {
            IngredientsSetter setAllergens(Collection<AllergenDto> allergenDtos) {
                Builder.this.result.allergens = allergenDtos;
                return new IngredientsSetter();
            }
        }

        class IngredientsSetter {
            NutritionSetter setIngredients(Collection<IngredientDto> ingredients) {
                Builder.this.result.ingredients = ingredients;
                return new NutritionSetter();
            }
        }

        class NutritionSetter {
            FinalBuilder setNutrition(NutritionDto nutritionDto) {
                Builder.this.result.nutrition = nutritionDto;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            ItemDto build() {
                return Builder.this.result;
            }
        }

    }
}
