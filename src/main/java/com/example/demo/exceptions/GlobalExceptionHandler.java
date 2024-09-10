package com.example.demo.exceptions;

import com.example.demo.product.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Creating a custom error response using ErrorResponse class
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(ProductNotValidException.class)
    public ResponseEntity<ErrorResponse> handleProductNotValidException(ProductNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getMessage()));
    }

    /**
     * This is one way to use Spring Started Validation
     * By having my own Custom Validations - refer to @ProductValidator and Built in Validation methods
     * this code will not be hit.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductNotValidConstraints(ConstraintViolationException exception) {
        // Just returns first error, not all of them
        return new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage());
    }
}
