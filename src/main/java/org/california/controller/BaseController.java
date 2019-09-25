package org.california.controller;

import org.california.util.exceptions.SendableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    protected Object result(Exception e) {
        return e instanceof SendableException ?
                e.getLocalizedMessage() : null;
    }


    protected HttpStatus status(Exception e) {
        if (!(e instanceof SendableException))
            e.printStackTrace();

        return e instanceof SendableException ?
                ((SendableException) e).getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
    }


}
