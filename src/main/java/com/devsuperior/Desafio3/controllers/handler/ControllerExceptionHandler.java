package com.devsuperior.Desafio3.controllers.handler;

import com.devsuperior.Desafio3.dtos.errors.CustomErrorDTO;
import com.devsuperior.Desafio3.dtos.errors.ValidationErrorDTO;
import com.devsuperior.Desafio3.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        ValidationErrorDTO error = new ValidationErrorDTO(Instant.now(), status.value(), "Erro de validação", request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addErrors(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }

}
