package com.whl.store.services.ex;

public class UserDeleteExeception extends ServicesException{
    public UserDeleteExeception() {
        super();
    }

    public UserDeleteExeception(String message) {
        super(message);
    }

    public UserDeleteExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDeleteExeception(Throwable cause) {
        super(cause);
    }

    protected UserDeleteExeception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
