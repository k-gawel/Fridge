package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Producer;
import org.california.util.ObjectUtils;

import java.io.Serializable;

@EqualsAndHashCode @ToString
@Getter
public class ProducerDto implements Serializable{

    private Long id;
    private String name;

    public static class Builder {

        private ProducerDto result = new ProducerDto();

        public NameSetter setId(Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(Producer producer) {
            return setId(producer != null ? producer.getId() : null);
        }

        public class NameSetter {
            public FinalBuilder setName(String name) {
                ObjectUtils.areAllNullOrNoneIs(name, Builder.this.result.id);

                Builder.this.result.name = name;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public ProducerDto build() {
                return Builder.this.result;
            }
        }

    }

}
