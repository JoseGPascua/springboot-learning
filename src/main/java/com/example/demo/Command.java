package com.example.demo;

import org.springframework.http.ResponseEntity;

/**
 * Creating an interface to add a layer of abstraction to the Service files created in the product package
 */
public interface Command <I, O>{
    ResponseEntity<O> execute(I input);
}
