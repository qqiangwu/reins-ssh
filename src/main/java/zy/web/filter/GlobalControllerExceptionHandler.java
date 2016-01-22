package zy.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import zy.exception.common.ForbiddenException;
import zy.web.util.ErrorCode;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorCode handleUnexpected(final Exception e) {
        log.error("Unexpected exception", e);
        return new ErrorCode("Service not available");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public ErrorCode handleBadInput(final ServletRequestBindingException e) {
        return new ErrorCode(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ErrorCode handleBadInput(final MethodArgumentTypeMismatchException e) {
        return new ErrorCode(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public ErrorCode handleBadInput(final ForbiddenException e) {
        return new ErrorCode(e.getMessage());
    }
}
