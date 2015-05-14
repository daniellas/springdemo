package com.recruitiva.demo.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void validationException(ConstraintViolationException e, HttpServletResponse resp) throws IOException {
        ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();
        LOGGER.error("Validation error: {}", violation);
        String message = violation.getMessage();

        resp.setHeader("X-ErrMsg", message);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void accessException(HttpServletResponse resp) {
        resp.setHeader("X-ErrMsg", "Access denied");
    }

}
