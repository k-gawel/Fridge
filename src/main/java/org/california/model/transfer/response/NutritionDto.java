package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Nutrition;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@EqualsAndHashCode @ToString
@Getter
public class NutritionDto implements Serializable {

    private long id;
    private Double energyKj;
    private Double energyKcal;
    private Double fat;
    private Double saturatedFat;
    private Double carbohydrate;
    private Double sugar;
    private Double protein;
    private Double salt;


    public static class Builder {
        
        private NutritionDto result = new NutritionDto();

        public EnergySetter setId(Long id) {
            Builder.this.result.id = id;
            return new EnergySetter();
        }

        public EnergySetter setId(@NotNull Nutrition nutrition) {
            return setId(nutrition.getId());
        }

        public class EnergySetter {
            public FatSetter setEnergy(Double kj, Double kcal) {
                Builder.this.result.energyKj = kj;
                Builder.this.result.energyKcal = kcal;
                return new FatSetter();
            }
        }

        public class FatSetter {
            public SaturatedFatSetter setFat(Double fat) {
                Builder.this.result.fat = fat;
                return new SaturatedFatSetter();
            }
        }

        public class SaturatedFatSetter {
            public CarbohydrateSetter setSaturatedFat(Double saturatedFat) {
                Builder.this.result.saturatedFat = saturatedFat;
                return new CarbohydrateSetter();
            }
        }

        public class CarbohydrateSetter {
            public SugarSetter setCarbohydrate(Double carbohydrate) {
                Builder.this.result.carbohydrate = carbohydrate;
                return new SugarSetter();
            }
        }

        public class SugarSetter {
            public ProteinSetter setSugar(Double sugar) {
                Builder.this.result.sugar = sugar;
                return new ProteinSetter();
            }
        }

        public class ProteinSetter {
            public SaltSetter setProtein(Double protein) {
                Builder.this.result.protein = protein;
                return new SaltSetter();
            }
        }

        public class SaltSetter {
            public FinalBuilder setSalt(Double salt) {
                Builder.this.result.salt = salt;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public NutritionDto build() {
                return Builder.this.result;
            }
        }

    }
    
}
