package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class NutritionForm implements Serializable {

    public final Double energy_kj;
    public final Double energy_kcal;
    public final Double fat;
    public final Double saturated_fat;
    public final Double carbohydrate;
    public final Double sugar;
    public final Double protein;
    public final Double salt;

    @JsonCreator
    public NutritionForm(Double energy_kj, Double energy_kcal,
                         Double fat, Double saturated_fat,
                         Double carbohydrate, Double sugar,
                         Double protein, Double salt) {
        this.energy_kj = energy_kj;
        this.energy_kcal = energy_kcal;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.protein = protein;
        this.salt = salt;
    }


    @Override
    public String toString() {
        return "NutritionForm{" +
                "energy_kj=" + energy_kj +
                ", energy_kcal=" + energy_kcal +
                ", fat=" + fat +
                ", saturated_fat=" + saturated_fat +
                ", carbohydrate=" + carbohydrate +
                ", sugar=" + sugar +
                ", protein=" + protein +
                ", salt=" + salt +
                '}';
    }

}
