package org.california.model.transfer.response;

import java.io.Serializable;

public class ProducerDto implements Serializable{

    public Long id;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProducerDto that = (ProducerDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProducerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public static class Builder {

        private ProducerDto result = new ProducerDto();

        public void setId(Long id) {
            result.id = id;
        }

        public void setName(String name) {
            result.name = name;
        }


        public ProducerDto build() {
            if((result.id == null) != (result.name == null))
                throw new IllegalStateException("both id and null must not be null or must be null");
            return this.result;
        }

    }

}
