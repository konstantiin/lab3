package exceptions;

public class BusyLocationException extends Exception {
    public BusyLocationException() {
        super("Location is busy!");
    }
}
