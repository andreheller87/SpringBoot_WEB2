package com.mcardoso.backend.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String pMessage)  {
        super(pMessage);
    }
    public ObjectNotFoundException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }
}
