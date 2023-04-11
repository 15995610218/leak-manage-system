package com.whl.store.services.ex;

public class UserNotPermissionsException extends ServicesException{
    public UserNotPermissionsException() {
        super();
    }

    public UserNotPermissionsException(String message) {
        super(message);
    }

    public UserNotPermissionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotPermissionsException(Throwable cause) {
        super(cause);
    }

    protected UserNotPermissionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
