package exceptions;

public class FailedCalculatingHashException extends RuntimeException {

    public FailedCalculatingHashException(String message) {
        super(message);
    }

}
