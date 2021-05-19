package hu.otpmobil.ticket.exception;

import hu.otpmobil.common.dto.ReservationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = { PaymentException.class })
    public ResponseEntity<Object> handlePaymentException(PaymentException ex) {
        ReservationResult reservationResult = new ReservationResult();
        reservationResult.setSuccess(false);
        reservationResult.setErrorCode(ex.getError().getErrorCode());
        return new ResponseEntity<Object>(reservationResult, HttpStatus.BAD_REQUEST);
    }

}
