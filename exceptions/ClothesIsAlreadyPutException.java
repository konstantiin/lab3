package exceptions;

public class ClothesIsAlreadyPutException extends Exception {
    public ClothesIsAlreadyPutException() {
    }

    public ClothesIsAlreadyPutException(String message) {
        super(message);
    }

    public ClothesIsAlreadyPutException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClothesIsAlreadyPutException(Throwable cause) {
        super(cause);
    }

    public ClothesIsAlreadyPutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
