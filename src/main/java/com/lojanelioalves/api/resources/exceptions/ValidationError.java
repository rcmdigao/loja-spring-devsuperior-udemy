package com.lojanelioalves.api.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Instant timeStamp, String path) {
        super(status, msg, timeStamp, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String mensagem){
        errors.add(new FieldMessage(fieldName, mensagem));
    }

}
