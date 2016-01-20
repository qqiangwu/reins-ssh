package zy.support.datahub;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
 *
 * If the code works, it was written by qqiangwu at 11:12 PM 1/19/16, otherwise I
 * don't know who wrote it.
 *
 */
@Aspect
@Order(100)
@Component
@Slf4j(topic = "dataHub")
public class DataHubAdvice {
    @Autowired DataHub mDataHub;

    @Pointcut("@annotation(Publish)")
    public void methodExecution() {
    }

    @AfterReturning(pointcut = "methodExecution() && @annotation(publish)", returning = "result", argNames = "result, publish")
    public void invoke(final Object result, final Publish publish) throws Throwable {
        val topic = publish.value();

        if (topic == null) {
            log.warn("Unexpected topic null");
            return;
        }

        log.info("event={}", topic);
        mDataHub.notify(topic, result);
    }
}
