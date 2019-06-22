package org.california.model.transfer.response;

import org.california.model.entity.item.Producer;
import org.california.model.util.ObjectUtils;

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

        public NameSetter setId(Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(Producer producer) {
            return setId(producer != null ? producer.getId() : null);
        }

        class NameSetter {
            FinalBuilder setName(String name) {
                ObjectUtils.allAreNullOrNoneIs(name, Builder.this.result.id);

                Builder.this.result.name = name;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            ProducerDto build() {
                return Builder.this.result;
            }
        }

    }

}
