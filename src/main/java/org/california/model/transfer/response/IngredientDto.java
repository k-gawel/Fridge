package org.california.model.transfer.response;

import java.io.Serializable;

public class IngredientDto implements Serializable {

    public long id;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientDto that = (IngredientDto) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public static class Builder {

        private IngredientDto result = new IngredientDto();

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

        public IngredientDto build() {
            if(result.id == 0 || result.name == null)
                throw new IllegalStateException("id.zero|name.null");

            return result;
        }


    }

}
