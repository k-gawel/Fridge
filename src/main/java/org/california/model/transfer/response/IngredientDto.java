package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@ToString @EqualsAndHashCode
@Getter
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

        public class NameSetter {
            public FinalBuilder setName(String name) {
                Builder.this.result.name = name;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public IngredientDto build() {
                return Builder.this.result;
            }
        }

     }

}
