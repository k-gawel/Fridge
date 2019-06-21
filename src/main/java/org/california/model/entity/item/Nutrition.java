package org.california.model.entity.item;

import org.california.model.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Nutrition extends BaseEntity {

    @OneToOne
    private Item item;

    private Double energy_kj;
    private Double energy_kcal;
    private Double fat;
    private Double saturatedFat;
    private Double carbohydrate;
    private Double sugar;
    private Double protein;
    private Double salt;

    public Nutrition() {
    }

    public Double getEnergy_kj() {
        return energy_kj;
    }

    public void setEnergy_kj(Double energy_kj) {
        this.energy_kj = energy_kj;
    }

    public Double getEnergy_kcal() {
        return energy_kcal;
    }

    public void setEnergy_kcal(Double energy_kcal) {
        this.energy_kcal = energy_kcal;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(Double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getSalt() {
        return salt;
    }

    public void setSalt(Double salt) {
        this.salt = salt;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
