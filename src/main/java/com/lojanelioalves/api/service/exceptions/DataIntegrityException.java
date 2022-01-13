package com.lojanelioalves.api.service.exceptions;

public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public DataIntegrityException(String msg) {
        super(msg);
    }

    // Causa do erro.
    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
