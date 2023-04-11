package com.whl.store.services.ex;

public class LeakInsertException extends ServicesException{
    public LeakInsertException() {
        super();
    }

    public LeakInsertException(String message) {
        super(message);
    }

    public LeakInsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeakInsertException(Throwable cause) {
        super(cause);
    }

    protected LeakInsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
