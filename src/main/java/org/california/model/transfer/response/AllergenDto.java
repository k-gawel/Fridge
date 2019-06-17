package org.california.model.transfer.response;

import org.california.model.entity.Allergen;
import org.jetbrains.annotations.NotNull;

public class AllergenDto {

    public long id;
    public String name;
    public boolean contains;

    public static class Builder {

        private AllergenDto result = new AllergenDto();

        public NameSetter setId(Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Allergen allergen) {
            return setId(allergen.getId());
        }

        class NameSetter {
            ContainsSetter setName(String name) {
                Builder.this.result.name = name;
                return new ContainsSetter();
            }
        }

        class ContainsSetter {
            FinalBuilder doContains(boolean contains) {
                Builder.this.result.contains = contains;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            AllergenDto build() {
                return Builder.this.result;
            }
        }

    }

}
