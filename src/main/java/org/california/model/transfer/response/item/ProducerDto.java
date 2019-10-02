package org.california.model.transfer.response.item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Producer;
import org.california.model.transfer.response.BaseDto;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class ProducerDto implements Serializable, BaseDto<Producer> {

    private Long id;
    private String name;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            ProducerDto build();
        }

        public interface IdSetter {
            NameSetter withId(Long id);
        }

        public interface NameSetter {
            FinalBuilder withName(String name);
        }

        public static class InnerBuilder implements FinalBuilder, IdSetter, NameSetter {
            private ProducerDto result = new ProducerDto();

            public NameSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public FinalBuilder withName(String name) {
                result.name = name;
                return this;
            }

            public ProducerDto build() {
                return result;
            }
        }
    }


}
