package zy.web.filter;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean reqIDFilterRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(new RequestIDFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("reqIDFilter");

        return registration;
    }
}
