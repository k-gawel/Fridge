package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.entity.BaseNamedEntity;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode @ToString
public class NamedEntityDto implements Serializable {

    private Serializable id;
    private String name;

    public static class Builder {

        private NamedEntityDto result = new NamedEntityDto();

        public NameSetter setId(@NotNull Serializable id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull BaseNamedEntity entity) {
            return setId(entity.getId());
        }

        public class NameSetter {
            public FinalBuilder setName(@NotEmpty String name) {
                Builder.this.result.name = name;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public NamedEntityDto build() {
                return Builder.this.result;
            }
        }

    }

}
