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
        super(messageName(entityClass, identifer));
    }

    private static String messageName(Class<? extends BaseEntity> c, Object i) {
        var className = c.getSimpleName().toLowerCase();
        var identifer = i == null ? "null" : i.toString();
        return className + "." + identifer;
    }

}
