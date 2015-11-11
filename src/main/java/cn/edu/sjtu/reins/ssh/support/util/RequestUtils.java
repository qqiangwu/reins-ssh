package cn.edu.sjtu.reins.ssh.support.util;

import javax.servlet.http.HttpServletRequest;

/**
 * If the code works, it was written by qqiangwu at 8:41 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
public abstract class RequestUtils {
    public static final boolean isAjax(final HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
