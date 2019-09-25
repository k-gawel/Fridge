package org.california.model.transfer.response.item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
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
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            NutritionDto build();
        }

        public interface FatSetter {
            SaturatedFatSetter withFat(Double fat);
        }

        public interface ProteinSetter {
            SaltSetter withProtein(Double protein);
        }

        public interface EnergyKcalSetter {
            FatSetter withEnergyKcal(Double energyKcal);
        }

        public interface CarbohydrateSetter {
            SugarSetter withCarbohydrate(Double carbohydrate);
        }

        public interface SaltSetter {
            FinalBuilder withSalt(Double salt);
        }

        public interface SugarSetter {
            ProteinSetter withSugar(Double sugar);
        }

        public interface IdSetter {
            EnergyKjSetter withId(long id);
        }

        public interface EnergyKjSetter {
            EnergyKcalSetter withEnergyKj(Double energyKj);
        }

        public interface SaturatedFatSetter {
            CarbohydrateSetter withSaturatedFat(Double saturatedFat);
        }

        public static class InnerBuilder implements FinalBuilder, FatSetter, ProteinSetter, EnergyKcalSetter, CarbohydrateSetter, SaltSetter, SugarSetter, IdSetter, EnergyKjSetter, SaturatedFatSetter {
            private NutritionDto result = new NutritionDto();

            public EnergyKjSetter withId(long id) {
                result.id = id;
                return this;
            }

            public EnergyKcalSetter withEnergyKj(Double energyKj) {
                result.energyKj = energyKj;
                return this;
            }

            public FatSetter withEnergyKcal(Double energyKcal) {
                result.energyKcal = energyKcal;
                return this;
            }

            public SaturatedFatSetter withFat(Double fat) {
                result.fat = fat;
                return this;
            }

            public CarbohydrateSetter withSaturatedFat(Double saturatedFat) {
                result.saturatedFat = saturatedFat;
                return this;
            }

            public SugarSetter withCarbohydrate(Double carbohydrate) {
                result.carbohydrate = carbohydrate;
                return this;
            }

            public ProteinSetter withSugar(Double sugar) {
                result.sugar = sugar;
                return this;
            }

            public SaltSetter withProtein(Double protein) {
                result.protein = protein;
                return this;
            }

            public FinalBuilder withSalt(Double salt) {
                result.salt = salt;
                return this;
            }

            public NutritionDto build() {
                return result;
            }
        }
    }


}
