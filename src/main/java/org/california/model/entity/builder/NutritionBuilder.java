package org.california.model.entity.builder;

import org.california.model.entity.item.Nutrition;

public class NutritionBuilder {

    private final Nutrition result = new Nutrition();

    public FatSetter setEnergy(Double kJ, Double kCal) {
        NutritionBuilder.this.result.setEnergy_kj(kJ);
        NutritionBuilder.this.result.setEnergy_kcal(kCal);
        return new FatSetter();
    }

    public class FatSetter {

        public CarbohydrateSetter setFat(Double fat, Double saturated) {
            NutritionBuilder.this.result.setFat(fat);
            NutritionBuilder.this.result.setSaturatedFat(saturated);
            return new CarbohydrateSetter();
        }

    }

    public class CarbohydrateSetter {

        public ProteinsSetter setCarbohydrates(Double carbohydrates, Double sugar) {
            NutritionBuilder.this.result.setCarbohydrate(carbohydrates);
            NutritionBuilder.this.result.setSugar(sugar);
            return new ProteinsSetter();
        }

    }

    public class ProteinsSetter {

        public SaltSetter setProteins(Double proteins) {
            NutritionBuilder.this.result.setProtein(proteins);
            return new SaltSetter();
        }

    }

    public class SaltSetter {
        public FinalBuilder setSalt(Double salt) {
            NutritionBuilder.this.result.setSalt(salt);
            return new FinalBuilder();
        }
    }

    public class FinalBuilder {
        public Nutrition build() {
            return NutritionBuilder.this.result;
        }
    }

}
