package zy.exception.common;

import zy.exception.AppException;

/*
 *
 * If the code works, it was written by qqiangwu at 6:52 PM 1/22/16, otherwise I
 * don't know who wrote it.
 *
 */
public class ForbiddenException extends AppException {
    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException() {
        super("invalid access");
    }
}
