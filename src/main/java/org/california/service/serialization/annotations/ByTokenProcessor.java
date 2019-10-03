package org.california.service.serialization.annotations;

import org.california.model.entity.Account;
import org.california.service.getter.GetterService;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Parameter;

public class ByTokenProcessor {

    private final String token;
    private final Class<? extends Account> type;

    public ByTokenProcessor(@Nullable String token, @NotNull Parameter parameter) {
        if(!parameter.isAnnotationPresent(ByToken.class))
            throw new IllegalArgumentException("Parameter " + parameter.toString() + " isn't annotated with @ByToken.");
        if(!Account.class.isAssignableFrom(parameter.getType()))
            throw new IllegalArgumentException(parameter.getType() + " isn't assignable with @ByToken");

        this.token = token;
        this.type  = (Class<? extends Account>) parameter.getType();
    }


    @Nullable
    public Account getValue() {
        return GetterService.GETTER().accounts.getByToken(token);
    }


}
