package org.california.model.transfer.response.iteminstance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.transfer.response.BaseDto;
import org.joda.money.Money;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MoneyDto implements Serializable {

    private Number amount;
    private String currency;

    public MoneyDto(Number amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    private MoneyDto() {
    }


    public static class Builder {
        public AmountSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            MoneyDto build();
        }

        public interface AmountSetter {
            CurrencySetter withAmount(Number amount);
        }

        public interface CurrencySetter {
            FinalBuilder withCurrency(String currency);
        }

        public static class InnerBuilder implements FinalBuilder, AmountSetter, CurrencySetter {
            private MoneyDto result = new MoneyDto();

            public CurrencySetter withAmount(Number amount) {
                result.amount = amount;
                return this;
            }

            public FinalBuilder withCurrency(String currency) {
                result.currency = currency;
                return this;
            }

            public MoneyDto build() {
                return result;
            }
        }
    }
}
