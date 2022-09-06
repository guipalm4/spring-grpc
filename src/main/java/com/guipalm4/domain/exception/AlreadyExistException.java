package com.guipalm4.domain.exception;


import io.grpc.Status;

public class AlreadyExistException extends BaseBusinessException {

    public static final String ERROR_MESSAGE = "Product %s already exist";
    private final String name;

    public AlreadyExistException(final String name) {
        super(String.format(ERROR_MESSAGE, name));
        this.name = name;
    }

    @Override
    public Status getStatusCode() {
        return Status.ALREADY_EXISTS;
    }

    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, name);
    }
}
