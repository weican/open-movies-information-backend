package ca.wchang.openMoives.controller;

import ca.wchang.openMoives.exception.MovieException;
import ca.wchang.openMoives.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MovieExceptionControllerAdvice {
    @ExceptionHandler(MovieException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleSecurityException(MovieException se) {
        ExceptionResponse response = new ExceptionResponse(se.getMessage());
        return response;
    }
}
