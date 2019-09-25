package org.california.util.exceptions;

import org.springframework.http.HttpStatus;

public abstract class SendableException extends RuntimeException {

    public abstract HttpStatus getStatus();

    public SendableException() {
    }

    public SendableException(String message) {
        super(message);
    }

    public SendableException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendableException(Throwable cause) {
        super(cause);
    }

    public SendableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
