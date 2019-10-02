package org.california.model.transfer.response.iteminstance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.InstanceChange;
import org.california.model.transfer.response.AccountDateDto;
import org.california.model.transfer.response.BaseDto;
import org.california.model.util.ChangeOnInstance;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Getter
public class InstanceChangeDto implements Serializable, BaseDto<InstanceChange> {

    private Long id;
    private ItemInstanceDto instance;
    private AccountDateDto changed;
    private ChangeOnInstance changeType;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            InstanceChangeDto build();
        }

        public interface InstanceSetter {
            ChangedSetter withInstance(ItemInstanceDto instance);
        }

        public interface IdSetter {
            InstanceSetter withId(Long id);
        }

        public interface ChangedSetter {
            ChangeTypeSetter withChanged(AccountDateDto changed);
        }

        public interface ChangeTypeSetter {
            FinalBuilder withChangeType(ChangeOnInstance changeType);
        }

        public static class InnerBuilder implements FinalBuilder, InstanceSetter, IdSetter, ChangedSetter, ChangeTypeSetter {
            private InstanceChangeDto result = new InstanceChangeDto();

            public InstanceSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public ChangedSetter withInstance(ItemInstanceDto instance) {
                result.instance = instance;
                return this;
            }

            public ChangeTypeSetter withChanged(AccountDateDto changed) {
                result.changed = changed;
                return this;
            }

            public FinalBuilder withChangeType(ChangeOnInstance changeType) {
                result.changeType = changeType;
                return this;
            }

            public InstanceChangeDto build() {
                return result;
            }
        }
    }


}
