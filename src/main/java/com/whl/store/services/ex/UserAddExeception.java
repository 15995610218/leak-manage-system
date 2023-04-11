package com.whl.store.services.ex;

public class UserAddExeception extends ServicesException{
    public UserAddExeception() {
        super();
    }

    public UserAddExeception(String message) {
        super(message);
    }

    public UserAddExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAddExeception(Throwable cause) {
        super(cause);
    }

    protected UserAddExeception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
