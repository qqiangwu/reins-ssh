package zy.exception.blog;

import zy.exception.AppException;

/*
 *
 * If the code works, it was written by qqiangwu at 11:01 AM 1/13/16, otherwise I
 * don't know who wrote it.
 *
 */
public class BlogException extends AppException {
    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }
}
