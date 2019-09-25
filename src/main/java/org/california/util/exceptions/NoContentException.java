package org.california.util.exceptions;

import org.california.model.entity.BaseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends SendableException {

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NO_CONTENT;
    }

    public NoContentException(Class<? extends BaseEntity> entityClass, Object identifer) {
        super(entityClass.getName() + " of " + identifer + " doesn't exists.");
    }

}
