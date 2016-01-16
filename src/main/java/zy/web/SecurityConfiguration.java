package zy.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import zy.domain.ZyUserDetails;
import zy.web.filter.CsrfHeaderFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired UserDetailsService mUserDetailsService;
    @Autowired ObjectMapper mObjectMapper;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .userDetailsService(mUserDetailsService)
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .exceptionHandling()
                    .authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and().formLogin()
                    .passwordParameter("password")
                    .usernameParameter("email")
                    .loginProcessingUrl("/login")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            val details = (ZyUserDetails) authentication.getPrincipal();
                            val user = details.getUser();
                            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            mObjectMapper.writeValue(httpServletResponse.getOutputStream(), user);
                            httpServletResponse.getOutputStream().flush();
                        }
                    })
                    .failureHandler((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST))
                .and().authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/users").authenticated()
                    .antMatchers(HttpMethod.POST, "/api/blogs", "/api/blogs/*", "/api/blogs/*/comments").authenticated()
                    .antMatchers(HttpMethod.PUT, "/api/blogs/*").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/api/blogs/*").authenticated()
                    .anyRequest().permitAll()
                .and().logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .and().csrf()
                    .csrfTokenRepository(csrfTokenRepository());
    }

    private final CsrfTokenRepository csrfTokenRepository() {
        final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
