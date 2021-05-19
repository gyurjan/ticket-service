package hu.otpmobil.ticket.exception;

import hu.otpmobil.common.error.Errors;

public class PartnerConnectionException extends RuntimeException {

    private final Errors error;

    public PartnerConnectionException(Errors error) {
        this.error = error;
    }

    public Errors getError() {
        return error;
    }
}
