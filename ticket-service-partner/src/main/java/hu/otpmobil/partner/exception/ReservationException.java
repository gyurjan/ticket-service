package hu.otpmobil.partner.exception;

import hu.otpmobil.common.error.Errors;

public class ReservationException extends RuntimeException {

    private Errors error;

    public ReservationException(Errors error) {
        super();
        this.error = error;
    }

    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }
}
