package com.whl.store.services.ex;

public class UserAndRoleAddException extends ServicesException{
    public UserAndRoleAddException() {
        super();
    }

    public UserAndRoleAddException(String message) {
        super(message);
    }

    public UserAndRoleAddException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAndRoleAddException(Throwable cause) {
        super(cause);
    }

    protected UserAndRoleAddException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
