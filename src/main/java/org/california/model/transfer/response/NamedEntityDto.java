package org.california.model.transfer.response;

import java.io.Serializable;

public class NamedEntityDto implements Serializable {

    public Serializable id;
    public String name;

    public static class Builder {

        private NamedEntityDto result = new NamedEntityDto();

        public Builder setId(Serializable id) {
            result.id = id;
            return this;
        }

        public Builder setName(String name) {
            result.name = name;
            return this;
        }

        public NamedEntityDto build() {
            if(result.id == null || result.name == null)
                throw new IllegalStateException("name.null|id.null");

            return result;
        }

    }

}
