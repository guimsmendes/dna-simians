package br.com.guimsmendes.dnasimians.entrypoint.exception;

import br.com.guimsmendes.dnasimians.entrypoint.controller.SimianController;
import br.com.guimsmendes.dnasimians.usecase.exception.UseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGERERROR = LoggerFactory.getLogger(SimianController.class);

    @ExceptionHandler(UseCaseException.NoRecordsFound.class)
    public ResponseEntity<Object> handleNotFoundException(UseCaseException.NoRecordsFound ex) {
        LOGGERERROR.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleNotFoundException(IllegalArgumentException ex) {
        LOGGERERROR.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @ExceptionHandler(UseCaseException.InputOutOfBounds.class)
    public ResponseEntity<Object> handleNotFoundException(UseCaseException.InputOutOfBounds ex) {
        LOGGERERROR.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
