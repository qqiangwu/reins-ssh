package zy.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import zy.domain.ZyUserDetails;

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
                .formLogin()
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
                            httpServletResponse.getOutputStream().close();
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    })
                    .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/users").authenticated()
                    .antMatchers(HttpMethod.POST, "/api/blogs/*").authenticated()
                    .antMatchers(HttpMethod.PUT, "/api/blogs/*").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/api/blogs/*").authenticated()
                    .anyRequest().permitAll()
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(new LogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
                            httpServletResponse.getWriter().close();
                        }
                    })
                    .and()
                .csrf()
                    .disable();
    }
}
