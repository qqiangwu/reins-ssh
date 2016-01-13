package zy.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingSupport {
    public Logger getLogger(final String name) {
        return LoggerFactory.getLogger(name);
    }
}
