package com.example.demo.exceptions.handler;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UnicoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ResourceExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException e, HttpServletRequest request) {
        ErrorResponse err = new ErrorResponse(
                System.currentTimeMillis(),
                "Não encotrado",
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(UnicoException.class)
    public ResponseEntity<ErrorResponse> unicoException(UnicoException e, HttpServletRequest request) {
        ErrorResponse err = new ErrorResponse(
            System.currentTimeMillis(),
            "Informações duplicadas. ",
            e.getMessage(),
                request.getRequestURI()

        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        if (e instanceof SQLException) {
            ErrorResponse err = new ErrorResponse(
                    System.currentTimeMillis(),
                    "Erro no banco de dados. ",
                    e.getLocalizedMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(err);
        }
        ErrorResponse err = new ErrorResponse(
                System.currentTimeMillis(),
                "Erro interno no servidor. ",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
