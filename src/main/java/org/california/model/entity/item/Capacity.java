package org.california.model.entity.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.california.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Getter @Setter @ToString
public class Capacity extends BaseEntity {

    @Column
    private Double value;

    @Enumerated @Column
    private Unit unit;

    public Capacity() {
    }

    public Capacity(String string) {
        String pString = string.trim()
                                .replaceAll(",", ".")
                                .replaceAll("\\s+", "");

        if(!string.matches("\\d+.?\\d*(G|KG|L|ML|DAG)"))
            throw new IllegalArgumentException("Invalid string. Expected \"\\\\d+.?\\\\d*(G|KG|L|ML), found " + string);

        this.unit = Unit.getUnit(pString);
        assert unit != null;

        String nString = pString.replace(unit.toString(), "");
        this.value = Double.valueOf(nString);
    }

    public enum Unit {
        KG("KG"), DAG("DAG"), G("G"), L("L"), ML("ML");

        private final String unit;

        Unit(String type) {
            this.unit = type;
        }

        public String toString() {
            return unit;
        }

        public static Unit getUnit(String string) {
            if (StringUtils.isBlank(string))
                return null;
            else if(string.contains(KG.unit))
                return KG;
            else if(string.contains(DAG.unit))
                return DAG;
            else if(string.contains(G.unit))
                return G;
            else if(string.contains(ML.unit))
                return ML;
            else if(string.contains(L.unit))
                return L;
            else
                throw new IllegalArgumentException("String doesn't contains any unit " + string);
        }
    }

}
