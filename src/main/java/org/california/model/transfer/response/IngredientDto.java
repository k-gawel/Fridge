package org.california.model.transfer.response;

import org.california.model.entity.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class IngredientDto implements Serializable {

    public long id;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientDto that = (IngredientDto) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public static class Builder {

        private IngredientDto result = new IngredientDto();

        public NameSetter setId(Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Ingredient ingredient) {
            return setId(ingredient.getId());
        }

        class NameSetter {
            FinalBuilder setName(String name) {
                Builder.this.result.name = name;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            IngredientDto build() {
                return Builder.this.result;
            }
        }

     }

}
