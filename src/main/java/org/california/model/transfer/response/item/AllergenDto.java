package org.california.model.transfer.response.item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Allergen;
import org.california.model.transfer.response.BaseDto;

@EqualsAndHashCode
@ToString
@Getter
public class AllergenDto implements BaseDto<Allergen> {

    private long id;
    private String name;
    private boolean contains;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            AllergenDto build();
        }

        public interface NameSetter {
            ContainsSetter withName(String name);
        }

        public interface IdSetter {
            NameSetter withId(long id);
        }

        public interface ContainsSetter {
            FinalBuilder withContains(boolean contains);
        }

        public static class InnerBuilder implements FinalBuilder, NameSetter, IdSetter, ContainsSetter {
            private AllergenDto result = new AllergenDto();

            public NameSetter withId(long id) {
                result.id = id;
                return this;
            }

            public ContainsSetter withName(String name) {
                result.name = name;
                return this;
            }

            public FinalBuilder withContains(boolean contains) {
                result.contains = contains;
                return this;
            }

            public AllergenDto build() {
                return result;
            }
        }
    }


}
