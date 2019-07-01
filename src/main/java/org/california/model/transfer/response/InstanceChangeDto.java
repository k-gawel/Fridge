package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.InstanceChange;
import org.california.model.util.ChangeOnInstance;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode @ToString
@Getter
public class InstanceChangeDto implements Serializable {

    private Long id;
    private ItemInstanceDto instance;
    private Long accountId;
    private LocalDate changeDate;
    private ChangeOnInstance changeType;
    
    public static class Builder {

        private InstanceChangeDto result = new InstanceChangeDto();

        public ItemInstanceDtoSetter setId(Long id) {
            Builder.this.result.id = id;
            return new ItemInstanceDtoSetter();
        }

        public ItemInstanceDtoSetter setId(@NotNull InstanceChange instanceChange) {
            return setId(instanceChange.getId());
        }

        public class ItemInstanceDtoSetter {
            public AccountIdSetter setItemInstanceDto(@NotNull ItemInstanceDto itemInstanceDto) {
                Builder.this.result.instance = itemInstanceDto;
                return new AccountIdSetter();
            }
        }

        public class AccountIdSetter {
            public ChangeDateSetter setAccountId(Long accountId) {
                Builder.this.result.accountId = accountId;
                return new ChangeDateSetter();
            }

            public ChangeDateSetter setAccountId(@NotNull Account account) {
                return setAccountId(account.getId());
            }
        }

        public class ChangeDateSetter {
            public ChangeTypeSetter setChangeDate(@NotNull LocalDate localDate) {
                Builder.this.result.changeDate = localDate;
                return new ChangeTypeSetter();
            }
        }

        public class ChangeTypeSetter {
            public FinalBuilder setChangeOnInstance(ChangeOnInstance changeType) {
                Builder.this.result.changeType = changeType;
                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public InstanceChangeDto build() {
                return Builder.this.result;
            }
        }

    }

}
