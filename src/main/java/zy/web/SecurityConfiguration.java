package zy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired UserDetailsService mUserDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .userDetailsService(mUserDetailsService)
                .httpBasic()
                    .authenticationEntryPoint(new AuthenticationEntryPoint() {
                        @Override
                        public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
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
