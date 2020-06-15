package org.example.order.service.input.ports.web;

import lombok.extern.slf4j.Slf4j;
import org.example.order.service.infrastructure.persistence.OrderNotFoundException;
import org.example.order.service.infrastructure.persistence.RepositoryAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    private ResponseEntity<String> catchValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors =  bindingResult.getAllErrors();
        StringBuilder reason = new StringBuilder();

        errors.forEach(error -> reason.append(error.getDefaultMessage()).append(";"));
        log.error(reason.toString());

        return new ResponseEntity<>(reason.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepositoryAccessException.class)
    private ResponseEntity<String> catchInfrastructureExceptions(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    private ResponseEntity<String> catchNotFoundException(OrderNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
