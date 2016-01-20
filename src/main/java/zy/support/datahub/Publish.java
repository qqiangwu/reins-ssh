package zy.support.datahub;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *
 * If the code works, it was written by qqiangwu at 11:11 PM 1/19/16, otherwise I
 * don't know who wrote it.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Publish {
    String value();
}
