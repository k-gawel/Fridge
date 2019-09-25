package org.california.model.transfer.request.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.california.model.transfer.request.utils.validator.FieldMatch;
import org.california.model.transfer.request.utils.validator.UniqueField;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Validated
@FieldMatch(first = "password1", second = "password2", message = "passwords.not_equal")
public class AccountForm extends Form implements Serializable {

    @NotBlank
    @Size(min = 5, max = 30, message = "name.length")
    @UniqueField(fieldType = UniqueField.FieldType.NAME, message = "name.already_in_use")
    public final String name;

    @NotBlank
    @Email(message = "email.wrong_format")
    @UniqueField(fieldType = UniqueField.FieldType.EMAIL, message = "emaio.already_in_use")
    public final String email;

    @NotBlank
    @Size(min = 6, max = 255, message = "password.length")
    public final String password1;

    @NotBlank
    @Size(min = 6, max = 255, message = "password.length")
    public final String password2;

    public AccountForm(String name, String email, String password1, String password2) {
        this.name = name;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;

    }

}
