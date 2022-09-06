package com.guipalm4.domain.exception.handler;

import com.guipalm4.domain.exception.BaseBusinessException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcExceptionHandlerImp implements ExceptionHandler {

    @GrpcExceptionHandler(BaseBusinessException.class)
    public StatusRuntimeException handleBusinessException(BaseBusinessException aException) {
        return aException.getStatusCode()
                .withCause(aException.getCause())
                .withDescription(aException.getErrorMessage())
                .asRuntimeException();
    }
}
