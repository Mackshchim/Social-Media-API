package tatar.mackshchim.sm.validation.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tatar.mackshchim.sm.validation.responses.ValidationErrorDTO;
import tatar.mackshchim.sm.validation.responses.ValidationErrorsDTO;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<ValidationErrorDTO> errors = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach( error -> {
            String errorMessage = error.getDefaultMessage();
            String fieldName = ((FieldError)error).getField();

            ValidationErrorDTO errorDTO = ValidationErrorDTO.builder()
                    .message(errorMessage)
                    .field(fieldName)
                    .build();

            errors.add(errorDTO);
        });

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ValidationErrorsDTO.builder()
                        .errors(errors)
                        .build());
    }
}
