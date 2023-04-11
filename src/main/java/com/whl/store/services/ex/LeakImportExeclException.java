package com.whl.store.services.ex;

public class LeakImportExeclException extends ServicesException{
    public LeakImportExeclException() {
        super();
    }

    public LeakImportExeclException(String message) {
        super(message);
    }

    public LeakImportExeclException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeakImportExeclException(Throwable cause) {
        super(cause);
    }

    protected LeakImportExeclException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
