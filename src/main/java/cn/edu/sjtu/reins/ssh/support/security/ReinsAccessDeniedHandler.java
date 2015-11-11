package cn.edu.sjtu.reins.ssh.support.security;

import cn.edu.sjtu.reins.ssh.support.util.RequestUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * If the code works, it was written by qqiangwu at 8:38 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
/**
 * 当一个【已经登录】的用户访问某个需要权限的地址（但是权限不够）时会抛出异常，在这里处理。
 * 如果这个请求时ajax请求，那么返回一个json对象。
 * 如果是普通的浏览器页面请求，那么定向到权限不足页面。
 */
@Component
public final class ReinsAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(final HttpServletRequest httpServletRequest,
                       final HttpServletResponse httpServletResponse,
                       final AccessDeniedException e) throws IOException, ServletException {
        if (RequestUtils.isAjax(httpServletRequest)) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/denied.do");
        }
    }
}
