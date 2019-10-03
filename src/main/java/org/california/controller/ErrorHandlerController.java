package org.california.controller;

import org.california.util.exceptions.SendableException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlerController {


    @ExceptionHandler
    public ResponseEntity handleException(Exception error) {
        if (error instanceof MethodArgumentNotValidException)
            return handleMethodArgumentNotValidException((MethodArgumentNotValidException) error);
        else if (error instanceof SendableException)
            return handleSendableException((SendableException) error);
        else if (error instanceof MissingServletRequestParameterException)
            return handleMissingServletRequestParameterException((MissingServletRequestParameterException) error);
        else
            return handleOtherException(error);
    }

    private ResponseEntity handleOtherException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    private ResponseEntity handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }


    private ResponseEntity handleSendableException(SendableException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getLocalizedMessage());
    }


    private ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> result = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
    }

}
