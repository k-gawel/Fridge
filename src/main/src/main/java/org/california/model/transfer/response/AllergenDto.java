package org.california.model.transfer.response;

public class AllergenDto {

    public long id;
    public String name;
    public boolean contains;

    public static class Builder {

        private AllergenDto result = new AllergenDto();

        public Builder() {}

        public Builder(long id, String name) {
            result.id = id;
            result.name = name;
        }

        public Builder setId(long id) {
            result.id = id;
            return this;
        }

        public Builder setName(String name) {
            result.name = name;
            return this;
        }

        public Builder setContains(boolean contains) {
            result.contains = contains;
            return this;
        }

        public AllergenDto build() {
            if(result.id == 0 || result.name == null)
                throw new IllegalStateException("id.zero|name.null");

            return result;
        }


    }

}
