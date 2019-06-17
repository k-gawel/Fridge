package org.california.model.transfer.response;

import org.california.model.entity.Account;
import org.california.model.entity.InstanceChange;
import org.california.model.util.ChangeOnInstance;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class InstanceChangeDto implements Serializable {


    public Long id;
    public ItemInstanceDto instance;
    public Long accountId;
    public LocalDate changeDate;
    public ChangeOnInstance changeType;



    public static class Builder {

        private InstanceChangeDto result = new InstanceChangeDto();

        public ItemInstanceDtoSetter setId(Long id) {
            Builder.this.result.id = id;
            return new ItemInstanceDtoSetter();
        }

        public ItemInstanceDtoSetter setId(@NotNull InstanceChange instanceChange) {
            return setId(instanceChange.getId());
        }

        class ItemInstanceDtoSetter {
            AccountIdSetter setItemInstanceDto(@NotNull ItemInstanceDto itemInstanceDto) {
                Builder.this.result.instance = itemInstanceDto;
                return new AccountIdSetter();
            }
        }

        class AccountIdSetter {
            ChangeDateSetter setAccountId(Long accountId) {
                Builder.this.result.accountId = accountId;
                return new ChangeDateSetter();
            }

            ChangeDateSetter setAccountId(@NotNull Account account) {
                return setAccountId(account.getId());
            }
        }

        class ChangeDateSetter {
            ChangeTypeSetter setChangeDate(@NotNull LocalDate localDate) {
                Builder.this.result.changeDate = localDate;
                return new ChangeTypeSetter();
            }
        }

        class ChangeTypeSetter {
            FinalBuilder setChangeOnInstance(ChangeOnInstance changeType) {
                Builder.this.result.changeType = changeType;
                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            InstanceChangeDto build() {
                return Builder.this.result;
            }
        }

    }

}
