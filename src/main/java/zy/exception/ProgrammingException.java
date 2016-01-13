package zy.exception;

public class ProgrammingException extends RuntimeException {
    public ProgrammingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProgrammingException(String message) {
        super(message);
    }
}
