package hu.otpmobile.ticket.api.exception;

import hu.otpmobil.common.error.Errors;

public class UserValidationException extends RuntimeException {

    private Errors error;

    public UserValidationException(Errors error) {
        this.error = error;
    }

    public Errors getError() {
        return error;
    }
}
