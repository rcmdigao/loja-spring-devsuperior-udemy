package com.lojanelioalves.api.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    // Causa do erro.
    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
