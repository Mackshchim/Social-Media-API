package tatar.mackshchim.sm.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tatar.mackshchim.sm.dto.exception.ExceptionDto;
import tatar.mackshchim.sm.exceptions.AlreadyExistsException;
import tatar.mackshchim.sm.exceptions.NotFoundException;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFound(NotFoundException e) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleAlreadyExists(AlreadyExistsException e) {
        return buildResponseEntity(HttpStatus.CONFLICT, e);
    }


    private ResponseEntity<ExceptionDto> buildResponseEntity(HttpStatus status, Exception e) {
        return ResponseEntity.status(status)
                .body(ExceptionDto.builder()
                        .message(e.getMessage())
                        .status(status.value())
                        .build());
    }

}
