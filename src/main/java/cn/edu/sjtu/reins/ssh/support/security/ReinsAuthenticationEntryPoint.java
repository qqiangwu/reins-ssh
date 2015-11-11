package cn.edu.sjtu.reins.ssh.support.security;

/**
 * If the code works, it was written by qqiangwu at 8:45 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */

import cn.edu.sjtu.reins.ssh.support.util.RequestUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当一个【未登录】的用户访问某个需要权限的地址时会抛出异常，在这里处理。
 * 如果这个请求时ajax请求，那么返回一个json对象。
 * 如果是普通的浏览器页面请求，那么定向到登录页面。
 */
@Component
public final class ReinsAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(final HttpServletRequest httpServletRequest,
                         final HttpServletResponse httpServletResponse,
                         final AuthenticationException e) throws IOException, ServletException {
        if (RequestUtils.isAjax(httpServletRequest)) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.do");
        }
    }
}
