package com.whl.store.services.ex;

public class UserNotRoleException extends ServicesException{
    public UserNotRoleException() {
        super();
    }

    public UserNotRoleException(String message) {
        super(message);
    }

    public UserNotRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotRoleException(Throwable cause) {
        super(cause);
    }

    protected UserNotRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
