package proiect.ProiectBiblioteca.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import proiect.ProiectBiblioteca.exceptions.MemberNotFoundException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handlePersonNotFound(Exception exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidField(MethodArgumentNotValidException exception) {
        System.out.println(exception.getMessage());
        String invalidFields = "Invalid fields: \n"
                + exception.getBindingResult().getFieldErrors().stream()
                .map(e -> "Field: " + e.getField() + ", error: " + e.getDefaultMessage() + ", value: " + e.getRejectedValue())
                .collect(Collectors.joining("\n"));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(invalidFields);
    }
}

