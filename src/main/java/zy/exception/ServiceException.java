package zy.exception;

/*
 *
 * If the code works, it was written by qqiangwu at 7:13 PM 1/22/16, otherwise I
 * don't know who wrote it.
 *
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
