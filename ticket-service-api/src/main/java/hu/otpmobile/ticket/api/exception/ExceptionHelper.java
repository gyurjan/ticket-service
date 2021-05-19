package hu.otpmobile.ticket.api.exception;

import feign.FeignException;
import hu.otpmobil.common.dto.ReservationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = { UserValidationException.class })
    public ResponseEntity<Object> handleUserValidationException(UserValidationException ex) {
        return new ResponseEntity<Object>(ex.getError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { FeignException.class })
    public ResponseEntity<Object> handlePaymentException(FeignException ex) {
        return new ResponseEntity<Object>(ex.contentUTF8(), HttpStatus.BAD_REQUEST);
    }

}
