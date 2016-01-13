package zy.exception.user;

public final class EmailAlreadyFoundException extends UserException {
    public EmailAlreadyFoundException(String message) {
        super(message);
    }
}
