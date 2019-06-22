package org.california.model.entity.item;

import lombok.Getter;
import lombok.Setter;
import org.california.model.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
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

}
