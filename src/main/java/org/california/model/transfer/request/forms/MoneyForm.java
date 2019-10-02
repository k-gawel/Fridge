package org.california.model.transfer.request.forms;

import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Validated
@ToString
public class MoneyForm extends Form implements Serializable {

    @PositiveOrZero(message = "price.negative")
    public final Number amount;
    public final String currency;

    public MoneyForm(Number amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

}
