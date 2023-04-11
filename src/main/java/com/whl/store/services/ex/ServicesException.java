package com.whl.store.services.ex;

/**
 * 业务层异常错误信息
 */
public class ServicesException extends RuntimeException{
    public ServicesException() {
        super();
    }

    public ServicesException(String message) {
        super(message);
    }

    public ServicesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicesException(Throwable cause) {
        super(cause);
    }

    protected ServicesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
