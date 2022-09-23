package cz.pvsps.corsitask.exceptions;

public class InvalidValueException extends Exception {
    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValueException(Throwable cause) {
        super(cause);
    }
}
