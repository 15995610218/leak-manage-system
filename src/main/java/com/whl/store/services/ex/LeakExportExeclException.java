package com.whl.store.services.ex;

public class LeakExportExeclException extends ServicesException{
    public LeakExportExeclException() {
        super();
    }

    public LeakExportExeclException(String message) {
        super(message);
    }

    public LeakExportExeclException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeakExportExeclException(Throwable cause) {
        super(cause);
    }

    protected LeakExportExeclException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
