package exceptions;

public class AwakeException extends Exception {
    public AwakeException() {
    }

    public AwakeException(String message) {
        super(message);
    }

    public AwakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AwakeException(Throwable cause) {
        super(cause);
    }

    public AwakeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
