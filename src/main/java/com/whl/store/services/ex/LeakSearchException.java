package com.whl.store.services.ex;

public class LeakSearchException extends ServicesException{
    public LeakSearchException() {
        super();
    }

    public LeakSearchException(String message) {
        super(message);
    }

    public LeakSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeakSearchException(Throwable cause) {
        super(cause);
    }

    protected LeakSearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
