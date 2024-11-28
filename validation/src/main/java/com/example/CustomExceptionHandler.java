package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Component
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> handleBindingException(MethodArgumentNotValidException e) {
        FieldError error = e.getFieldError();
        String errorMessage ;
        if(Objects.isNull(error)) {
            return ResponseEntity.badRequest().body(" field not valid.");
        }else{
            errorMessage = error.getField() + " " + error.getDefaultMessage();
        }
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
