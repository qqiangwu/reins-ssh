package zy.web;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/users**").permitAll()
                .anyRequest().permitAll();
    }
}
