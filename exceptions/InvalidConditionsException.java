package exceptions;

public class InvalidConditionsException extends Exception {
    public InvalidConditionsException() {
    }

    public InvalidConditionsException(String message) {
        super(message);
    }

    public InvalidConditionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidConditionsException(Throwable cause) {
        super(cause);
    }

    public InvalidConditionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
