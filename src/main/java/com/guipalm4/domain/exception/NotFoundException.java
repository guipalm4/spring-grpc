package com.guipalm4.domain.exception;


import io.grpc.Status;

public class NotFoundException extends BaseBusinessException {

    public static final String ERROR_MESSAGE = "Product with ID '%s' not found in database.";
    private final String id;

    public NotFoundException(final String id) {
        super(String.format(ERROR_MESSAGE, id));
        this.id = id;
    }

    @Override
    public Status getStatusCode() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, id);
    }
}
