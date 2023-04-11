package com.whl.store.services.ex;

public class LeakUpdateException extends ServicesException{
    public LeakUpdateException() {
        super();
    }

    public LeakUpdateException(String message) {
        super(message);
    }

    public LeakUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeakUpdateException(Throwable cause) {
        super(cause);
    }

    protected LeakUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
