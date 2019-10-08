package org.california.model.transfer.response.iteminstance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.transfer.response.item.ItemDto;

import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Getter
public class ItemInstancesDto implements Serializable {

    private Collection<ItemDto> items;
    private Collection<ItemInstanceDto> instances;


    public static class Builder {
        public ItemsSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            ItemInstancesDto build();
        }

        public interface InstancesSetter {
            FinalBuilder withInstances(Collection<ItemInstanceDto> instances);
        }

        public interface ItemsSetter {
            InstancesSetter withItems(Collection<ItemDto> items);
        }

        public static class InnerBuilder implements FinalBuilder, InstancesSetter, ItemsSetter {
            private ItemInstancesDto result = new ItemInstancesDto();

            public InstancesSetter withItems(Collection<ItemDto> items) {
                result.items = items;
                return this;
            }

            public FinalBuilder withInstances(Collection<ItemInstanceDto> instances) {
                result.instances = instances;
                return this;
            }

            public ItemInstancesDto build() {
                return result;
            }
        }
    }


}
