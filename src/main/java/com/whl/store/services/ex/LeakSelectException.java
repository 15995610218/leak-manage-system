package com.whl.store.services.ex;

import com.whl.store.services.ex.ServicesException;

public class LeakSelectException extends ServicesException {
    public LeakSelectException() {
        super();
    }

    public LeakSelectException(String message) {
        super(message);
    }

    public LeakSelectException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeakSelectException(Throwable cause) {
        super(cause);
    }

    protected LeakSelectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
