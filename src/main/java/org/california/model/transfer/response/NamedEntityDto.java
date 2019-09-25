package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class NamedEntityDto implements Serializable {

    private Serializable id;
    private String name;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            NamedEntityDto build();
        }

        public interface IdSetter {
            NameSetter withId(Serializable id);
        }

        public interface NameSetter {
            FinalBuilder withName(String name);
        }

        public static class InnerBuilder implements FinalBuilder, IdSetter, NameSetter {
            private NamedEntityDto result = new NamedEntityDto();

            public NameSetter withId(Serializable id) {
                result.id = id;
                return this;
            }

            public FinalBuilder withName(String name) {
                result.name = name;
                return this;
            }

            public NamedEntityDto build() {
                return result;
            }
        }
    }


}
