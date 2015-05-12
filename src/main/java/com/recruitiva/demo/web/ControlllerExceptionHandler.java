package com.recruitiva.demo.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControlllerExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void validationException(ConstraintViolationException e,HttpServletResponse resp) throws IOException {
        String message = "Błąd walidacji: " + e.getConstraintViolations().iterator().next().getMessage();
        
        resp.getOutputStream().write(message.getBytes());
        resp.getOutputStream().close();
        
    }
}
