package org.california.model.transfer.response;

import org.california.model.entity.BaseNamedEntity;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class NamedEntityDto implements Serializable {

    public Serializable id;
    public String name;

    public static class Builder {

        private NamedEntityDto result = new NamedEntityDto();

        public NameSetter setId(@NotNull Serializable id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull BaseNamedEntity entity) {
            return setId(entity.getId());
        }

        class NameSetter {
            FinalBuilder setName(@NotEmpty String name) {
                Builder.this.result.name = name;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            NamedEntityDto build() {
                return Builder.this.result;
            }
        }

    }

}
