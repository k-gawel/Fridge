package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Allergen;
import org.jetbrains.annotations.NotNull;

@EqualsAndHashCode @ToString
@Getter
public class AllergenDto {

    private long id;
    private String name;
    private boolean contains;

    public static class Builder {
        private AllergenDto result = new AllergenDto();

        public NameSetter setId(Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Allergen allergen) {
            return setId(allergen.getId());
        }

        public class NameSetter {
            public ContainsSetter setName(String name) {
                Builder.this.result.name = name;
                return new ContainsSetter();
            }
        }

        public class ContainsSetter {
            public FinalBuilder doContains(boolean contains) {
                Builder.this.result.contains = contains;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public AllergenDto build() {
                return Builder.this.result;
            }
        }
    }

}
