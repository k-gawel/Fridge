package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.graalvm.compiler.lir.CompositeValue;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Validated
public class NutritionForm implements Serializable {

    @PositiveOrZero(message = "energy_kj.negative")
    public final Double energy_kj;

    @PositiveOrZero(message = "energy_kcal.negative")
    public final Double energy_kcal;

    @PositiveOrZero(message = "fat.negative")
    public final Double fat;

    @PositiveOrZero(message = "saturated_fat.negative")
    public final Double saturated_fat;

    @PositiveOrZero(message = "carbohydrate.negative")
    public final Double carbohydrate;

    @PositiveOrZero(message = "sugar.negative")
    public final Double sugar;

    @PositiveOrZero(message = "protein.negative")
    public final Double protein;

    @PositiveOrZero(message = "salt.negative")
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
