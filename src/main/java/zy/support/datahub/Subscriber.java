package zy.support.datahub;

/*
 *
 * If the code works, it was written by qqiangwu at 10:05 AM 1/20/16, otherwise I
 * don't know who wrote it.
 *
 */
public interface Subscriber<T> {
    void onNotified(T arg);
}
