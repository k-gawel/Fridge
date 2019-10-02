package org.california.model.transfer.response.item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Ingredient;
import org.california.model.transfer.response.BaseDto;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Getter
public class IngredientDto implements Serializable, BaseDto<Ingredient> {

    private long id;
    private String name;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            IngredientDto build();
        }

        public interface NameSetter {
            FinalBuilder withName(String name);
        }

        public interface IdSetter {
            NameSetter withId(long id);
        }

        public static class InnerBuilder implements FinalBuilder, NameSetter, IdSetter {
            private IngredientDto result = new IngredientDto();

            public NameSetter withId(long id) {
                result.id = id;
                return this;
            }

            public FinalBuilder withName(String name) {
                result.name = name;
                return this;
            }

            public IngredientDto build() {
                return result;
            }
        }
    }


}
