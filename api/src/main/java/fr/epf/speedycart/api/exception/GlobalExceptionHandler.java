package fr.epf.speedycart.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>("Product not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ShopNotFoundException e) {
        return new ResponseEntity<>("Shop not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(AddressNotFoundException e) {
        return new ResponseEntity<>("Address not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>("Invalid statement: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
