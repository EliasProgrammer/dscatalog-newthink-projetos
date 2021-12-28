package com.devsuperior.projetoCapitulo1.resources.exception;

import com.devsuperior.projetoCapitulo1.services.exceptions.ResouceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StandardError> entityNotFound(ResouceNotFoundException exception, HttpServletRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        StandardError err = getStandardError("Resource not found", notFound, exception, request);

        return ResponseEntity.status(notFound).body(err);
    }

    private StandardError getStandardError(String message, HttpStatus status, Exception exception, HttpServletRequest request) {
        StandardError err = new StandardError();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(message);
        err.setMessage(exception.getMessage());
        err.setPath(request.getRequestURI());

        return err;
    }


}
