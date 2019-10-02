package org.california.model.transfer.response.item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Item;
import org.california.model.transfer.response.BaseDto;

import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Getter
public class ItemDto implements Serializable, BaseDto<Item> {

    private Long id;
    private String name;
    private Long barcode;

    private Long placeId;
    private Long categoryId;
    private ProducerDto producer;

    private String description;
    private String storage;
    private String capacity;

    private Collection<AllergenDto> allergens;
    private Collection<IngredientDto> ingredients;
    private NutritionDto nutrition;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface CapacitySetter {
            AllergensSetter withCapacity(String capacity);
        }

        public interface FinalBuilder {
            ItemDto build();
        }

        public interface CategoryIdSetter {
            ProducerSetter withCategoryId(Long categoryId);
        }

        public interface DescriptionSetter {
            StorageSetter withDescription(String description);
        }

        public interface StorageSetter {
            CapacitySetter withStorage(String storage);
        }

        public interface AllergensSetter {
            IngredientsSetter withAllergens(Collection<AllergenDto> allergens);
        }

        public interface IngredientsSetter {
            NutritionSetter withIngredients(Collection<IngredientDto> ingredients);
        }

        public interface ProducerSetter {
            DescriptionSetter withProducer(ProducerDto producer);
        }

        public interface NameSetter {
            BarcodeSetter withName(String name);
        }

        public interface IdSetter {
            NameSetter withId(Long id);
        }

        public interface PlaceIdSetter {
            CategoryIdSetter withPlaceId(Long placeId);
        }

        public interface BarcodeSetter {
            PlaceIdSetter withBarcode(Long barcode);
        }

        public interface NutritionSetter {
            FinalBuilder withNutrition(NutritionDto nutrition);
        }

        public static class InnerBuilder implements CapacitySetter, FinalBuilder, CategoryIdSetter, DescriptionSetter, StorageSetter, AllergensSetter, IngredientsSetter, ProducerSetter, NameSetter, IdSetter, PlaceIdSetter, BarcodeSetter, NutritionSetter {
            private ItemDto result = new ItemDto();

            public NameSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public BarcodeSetter withName(String name) {
                result.name = name;
                return this;
            }

            public PlaceIdSetter withBarcode(Long barcode) {
                result.barcode = barcode;
                return this;
            }

            public CategoryIdSetter withPlaceId(Long placeId) {
                result.placeId = placeId;
                return this;
            }

            public ProducerSetter withCategoryId(Long categoryId) {
                result.categoryId = categoryId;
                return this;
            }

            public DescriptionSetter withProducer(ProducerDto producer) {
                result.producer = producer;
                return this;
            }

            public StorageSetter withDescription(String description) {
                result.description = description;
                return this;
            }

            public CapacitySetter withStorage(String storage) {
                result.storage = storage;
                return this;
            }

            public AllergensSetter withCapacity(String capacity) {
                result.capacity = capacity;
                return this;
            }

            public IngredientsSetter withAllergens(Collection<AllergenDto> allergens) {
                result.allergens = allergens;
                return this;
            }

            public NutritionSetter withIngredients(Collection<IngredientDto> ingredients) {
                result.ingredients = ingredients;
                return this;
            }

            public FinalBuilder withNutrition(NutritionDto nutrition) {
                result.nutrition = nutrition;
                return this;
            }

            public ItemDto build() {
                return result;
            }
        }
    }


}
