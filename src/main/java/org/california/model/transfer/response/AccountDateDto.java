package org.california.model.transfer.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.entity.utils.AccountDate;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AccountDateDto implements Serializable, BaseDto<AccountDate> {

    private Number account;
    private LocalDate date;

    public AccountDateDto(AccountDate accountDate) {
        this.account = accountDate.getAccount().getId();
        this.date = accountDate.getDate();
    }

    public AccountDateDto(Number account, LocalDate date) {
        this.account = account;
        this.date = date;
    }

    private AccountDateDto() {
    }


    public static class Builder {
        public AccountSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            AccountDateDto build();
        }

        public interface DateSetter {
            FinalBuilder withDate(LocalDate date);
        }

        public interface AccountSetter {
            DateSetter withAccount(Number account);
        }

        public static class InnerBuilder implements FinalBuilder, DateSetter, AccountSetter {
            private AccountDateDto result = new AccountDateDto();

            public DateSetter withAccount(Number account) {
                result.account = account;
                return this;
            }

            public FinalBuilder withDate(LocalDate date) {
                result.date = date;
                return this;
            }

            public AccountDateDto build() {
                return result;
            }
        }
    }


}
