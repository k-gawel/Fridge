package org.california.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotValidException extends SendableException {

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public NotValidException(String message) {
        super(message);
    }

}
