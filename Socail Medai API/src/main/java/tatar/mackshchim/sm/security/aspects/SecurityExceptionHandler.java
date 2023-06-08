package tatar.mackshchim.sm.security.aspects;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tatar.mackshchim.sm.security.exceptions.RefreshTokenException;
import tatar.mackshchim.sm.security.responses.SecurityExceptionDTO;

@ControllerAdvice
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({JWTVerificationException.class, RefreshTokenException.class})
    public ResponseEntity<SecurityExceptionDTO> handleJWTVerificationException(Exception e) {
        return buildResponseEntity(HttpStatus.UNAUTHORIZED, e);
    }


    private ResponseEntity<SecurityExceptionDTO> buildResponseEntity(HttpStatus status, Exception e) {
        return ResponseEntity.status(status)
                .body(SecurityExceptionDTO.builder()
                        .message(e.getMessage())
                        .status(status.value())
                        .build());
    }
}
