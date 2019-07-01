package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.Place;
import org.california.model.entity.item.Capacity;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode @ToString
@Getter
public class ItemDto implements Serializable {

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
        
        private ItemDto result = new ItemDto();

        public NameSetter setId(@NotNull Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Item item) {
            return setId(item.getId());
        }

        public class NameSetter {
            public BarcodeSetter setName(@NotEmpty String name) {
                Builder.this.result.name = name;
                return new BarcodeSetter();
            }

            public BarcodeSetter setName(@NotNull Item item) {
                return setName(item.getName());
            }
        }

        public class BarcodeSetter {
            public PlaceIdSetter setBarcode(Long barcode) {
                Builder.this.result.barcode = barcode;
                return new PlaceIdSetter();
            }

            public PlaceIdSetter setBarcode(@NotNull Item item) {
                return setBarcode(item.getBarcode());
            }
        }

        public class PlaceIdSetter {
            public CategoryIdSetter setPlaceId(Long placeId) {
                Builder.this.result.placeId = placeId;
                return new CategoryIdSetter();
            }

            public CategoryIdSetter setPlaceId(Place place) {
                return setPlaceId(place != null ? place.getId() : null);
            }
        }

        public class CategoryIdSetter {
            public ProducerSetter setCategoryId(@NotNull Long categoryId) {
                Builder.this.result.categoryId = categoryId;
                return new ProducerSetter();
            }
            
            public ProducerSetter setCategoryId(@NotNull Category category) {
                return setCategoryId(category.getId());
            }
        }

        public class ProducerSetter {
            public DescriptionSetter setProducer(ProducerDto producerDto) {
                Builder.this.result.producer = producerDto;
                return new DescriptionSetter();
            }
        }

        public class DescriptionSetter {
            public StorageSetter setDescription(String description) {
                Builder.this.result.description = description;
                return new StorageSetter();
            }

            public StorageSetter setDescription(Item item) {
                return setDescription(item.getDescription());
            }
        }

        public class StorageSetter {
            public CapacitySetter setStorage(String storage) {
                Builder.this.result.storage = storage;
                return new CapacitySetter();
            }

            public CapacitySetter setStorage(@NotNull Item item) {
                return setStorage(item.getStorage());
            }
        }

        public class CapacitySetter {

            public AllergensSetter setCapacity(Capacity capacity) {
                if(capacity != null)
                    Builder.this.result.capacity = capacity.getValue() + ";" + capacity.getUnit().toString();
                return new AllergensSetter();
            }

            public AllergensSetter setCapacity(Item item) {
                return setCapacity(item.getCapacity());
            }

        }

        public class AllergensSetter {
            public IngredientsSetter setAllergens(Collection<AllergenDto> allergenDtos) {
                Builder.this.result.allergens = allergenDtos;
                return new IngredientsSetter();
            }
        }

        public class IngredientsSetter {
            public NutritionSetter setIngredients(Collection<IngredientDto> ingredients) {
                Builder.this.result.ingredients = ingredients;
                return new NutritionSetter();
            }
        }

        public class NutritionSetter {
            public FinalBuilder setNutrition(NutritionDto nutritionDto) {
                Builder.this.result.nutrition = nutritionDto;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public ItemDto build() {
                return Builder.this.result;
            }
        }

    }
}
