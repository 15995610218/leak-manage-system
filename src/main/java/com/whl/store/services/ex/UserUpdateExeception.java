package com.whl.store.services.ex;

public class UserUpdateExeception extends ServicesException{
    public UserUpdateExeception() {
        super();
    }

    public UserUpdateExeception(String message) {
        super(message);
    }

    public UserUpdateExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public UserUpdateExeception(Throwable cause) {
        super(cause);
    }

    protected UserUpdateExeception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
