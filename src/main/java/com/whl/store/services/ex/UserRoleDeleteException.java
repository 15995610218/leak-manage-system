package com.whl.store.services.ex;

public class UserRoleDeleteException extends ServicesException{
    public UserRoleDeleteException() {
        super();
    }

    public UserRoleDeleteException(String message) {
        super(message);
    }

    public UserRoleDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRoleDeleteException(Throwable cause) {
        super(cause);
    }

    protected UserRoleDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
