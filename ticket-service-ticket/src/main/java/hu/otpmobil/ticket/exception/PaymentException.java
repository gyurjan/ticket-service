package hu.otpmobil.ticket.exception;

import hu.otpmobil.common.error.Errors;

public class PaymentException extends RuntimeException {

    private final Errors error;

    public PaymentException(Errors error) {
        super();
        this.error = error;
    }

    public Errors getError() {
        return error;
    }
}
