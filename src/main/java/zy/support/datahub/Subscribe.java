package zy.support.datahub;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *
 * If the code works, it was written by qqiangwu at 10:04 AM 1/20/16, otherwise I
 * don't know who wrote it.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface Subscribe {
    String value();
}
