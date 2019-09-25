package org.california.model.transfer.response.place;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.transfer.response.iteminstance.ItemInstanceDto;

import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Getter
public class ContainerDto implements Serializable {

    private Long id;
    private String name;
    private Long place_id;
    private Collection<ItemInstanceDto> instances;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            ContainerDto build();
        }

        public interface NameSetter {
            Place_idSetter withName(String name);
        }

        public interface InstancesSetter {
            FinalBuilder withInstances(Collection<ItemInstanceDto> instances);
        }

        public interface IdSetter {
            NameSetter withId(Long id);
        }

        public interface Place_idSetter {
            InstancesSetter withPlace_id(Long place_id);
        }

        public static class InnerBuilder implements FinalBuilder, NameSetter, InstancesSetter, IdSetter, Place_idSetter {
            private ContainerDto result = new ContainerDto();

            public NameSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public Place_idSetter withName(String name) {
                result.name = name;
                return this;
            }

            public InstancesSetter withPlace_id(Long place_id) {
                result.place_id = place_id;
                return this;
            }

            public FinalBuilder withInstances(Collection<ItemInstanceDto> instances) {
                result.instances = instances;
                return this;
            }

            public ContainerDto build() {
                return result;
            }
        }
    }


}
