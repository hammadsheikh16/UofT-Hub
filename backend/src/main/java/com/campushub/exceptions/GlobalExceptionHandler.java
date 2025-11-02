package com.campushub.exceptions;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HubUserNotFoundException.class)
    public ResponseEntity<ErrorObject> handleHubUserNotFoundException(HubUserNotFoundException e, WebRequest request){
        ErrorObject err = new ErrorObject();


        err.setStatusCode(HttpStatus.NOT_FOUND.value());
        err.setMessage(e.getMessage());
        err.setTimestamp(new Date());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
