package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Validated
@ToString
public class MoneyForm extends Form implements Serializable {

    @PositiveOrZero
    public final Number amount;
    public final String currency;

    public MoneyForm(Number amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

}
