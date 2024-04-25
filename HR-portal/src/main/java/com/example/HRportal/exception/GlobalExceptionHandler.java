package com.example.HRportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // helps tp catch any kind of exception
public class GlobalExceptionHandler {

   //Existing exception customizing
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorObject> handleRuntimeException(RuntimeException e){
        ErrorObject responseObject = new ErrorObject();
        responseObject.setMessage(e.getMessage());
        responseObject.setStatusCode("C500");
        responseObject.setDateTime(LocalDateTime.now());
        return ResponseEntity.status(500).body(responseObject);
    }

    //Custom exception handling
    @ExceptionHandler(RecordNotFound.class)
    public ResponseEntity<ErrorObject> handleRuntimeException(RecordNotFound e){
        ErrorObject responseObject = new ErrorObject();
        responseObject.setMessage("Record Not Found!");
        responseObject.setStatusCode("C404");
        responseObject.setDateTime(LocalDateTime.now());
        return ResponseEntity.status(404).body(responseObject); // using builder to create response entity.
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFound ex){
        ErrorObject responseObject = new ErrorObject();
        responseObject.setStatusCode("C404");
        //responseObject.setStatus(HttpStatus.NOT_FOUND.value());
        responseObject.setMessage(ex.getLocalizedMessage());
        responseObject.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(responseObject,HttpStatus.NOT_FOUND); // using new response entity for creating response entity
    }
}
