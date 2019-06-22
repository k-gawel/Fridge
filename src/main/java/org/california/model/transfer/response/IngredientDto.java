package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.entity.item.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@ToString @EqualsAndHashCode
public class IngredientDto implements Serializable {

    private long id;
    private String name;
    
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
