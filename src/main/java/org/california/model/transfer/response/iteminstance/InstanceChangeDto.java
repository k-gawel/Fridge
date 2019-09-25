package org.california.model.transfer.response.iteminstance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.util.ChangeOnInstance;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Getter
public class InstanceChangeDto implements Serializable {

    private Long id;
    private ItemInstanceDto instance;
    private Long accountId;
    private LocalDate changeDate;
    private ChangeOnInstance changeType;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            InstanceChangeDto build();
        }

        public interface ChangeTypeSetter {
            FinalBuilder withChangeType(ChangeOnInstance changeType);
        }

        public interface InstanceSetter {
            AccountIdSetter withInstance(ItemInstanceDto instance);
        }

        public interface IdSetter {
            InstanceSetter withId(Long id);
        }

        public interface AccountIdSetter {
            ChangeDateSetter withAccountId(Long accountId);
        }

        public interface ChangeDateSetter {
            ChangeTypeSetter withChangeDate(LocalDate changeDate);
        }

        public static class InnerBuilder implements FinalBuilder, ChangeTypeSetter, InstanceSetter, IdSetter, AccountIdSetter, ChangeDateSetter {
            private InstanceChangeDto result = new InstanceChangeDto();

            public InstanceSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public AccountIdSetter withInstance(ItemInstanceDto instance) {
                result.instance = instance;
                return this;
            }

            public ChangeDateSetter withAccountId(Long accountId) {
                result.accountId = accountId;
                return this;
            }

            public ChangeTypeSetter withChangeDate(LocalDate changeDate) {
                result.changeDate = changeDate;
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
