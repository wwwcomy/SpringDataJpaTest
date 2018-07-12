package com.example.demo.controller.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlingAdvice {

    private final Log logger = LogFactory.getLog(getClass());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public Response handle(Exception ex) {
        logger.error("Entering exception handler for Exception");
        logger.error("Handling " + ex.getClass().toString() + ": " + ex.getMessage(), ex);
        return new Response("exception", "An error occurred");
    }

}
