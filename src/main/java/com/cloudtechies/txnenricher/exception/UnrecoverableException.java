package com.cloudtechies.txnenricher.exception;

public class UnrecoverableException extends RuntimeException{

    private static final long serialVersionUID=1L;

    public UnrecoverableException(String message){
        super(message);
    }
}
