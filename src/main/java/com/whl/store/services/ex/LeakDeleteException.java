package com.whl.store.services.ex;

public class LeakDeleteException extends ServicesException{
    public LeakDeleteException() {
        super();
    }

    public LeakDeleteException(String message) {
        super(message);
    }

    public LeakDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeakDeleteException(Throwable cause) {
        super(cause);
    }

    protected LeakDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
