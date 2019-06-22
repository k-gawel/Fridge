package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class AccountForm implements Serializable {

    public final String name;
    public final String email;
    public final String password1;
    public final String password2;

    @JsonCreator
    public AccountForm(String name, String email, String password1, String password2) {
        this.name = name;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
    }

}
