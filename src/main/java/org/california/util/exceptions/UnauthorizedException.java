package org.california.util.exceptions;

import org.california.model.entity.Account;
import org.california.model.entity.BaseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends SendableException {

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    public UnauthorizedException() {}

    public UnauthorizedException(String description) {
        super(description);
    }

    public UnauthorizedException(Account account, BaseEntity entity) {
        this(asString(account) + " x " + asString(entity));
    }

    private static String asString(Object object) {
        return object != null ? object.toString() : "NULL";
    }

}
