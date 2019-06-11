package org.california.model.transfer.response;

import java.io.Serializable;

public class NutritionDto implements Serializable {

    public long id;
    public Integer energy;
    public Double fat;
    public Double saturatedFat;
    public Double carbohydrate;
    public Double sugar;
    public Double protein;
    public Double salt;


    public static class Builder {
        
        private NutritionDto result = new NutritionDto();

        public Builder setEnergy(Integer energy) {
            result.energy = energy;
            return this;
        }

        public Builder setId(long id) {
            result.id = id;
            return this;
        }

        public Builder setFat(Double fat) {
            result.fat = fat;
            return this;
        }

        public Builder setSaturatedFat(Double saturatedFat) {
            result.saturatedFat = saturatedFat;
            return this;
        }

        public Builder setCarbohydrate(Double carbohydrate) {
            result.carbohydrate = carbohydrate;
            return this;
        }

        public Builder setSugar(Double sugar) {
            result.sugar = sugar;
            return this;
        }

        public Builder setProtein(Double protein) {
            result.protein = protein;
            return this;
        }

        public Builder setSalt(Double salt) {
            result.salt = salt;
            return this;
        }

        public NutritionDto build() {
            return result;
        }

    }
    
}
