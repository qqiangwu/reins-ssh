package zy.support.track;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import zy.support.LoggingSupport;

@Aspect
@Order(100)
@Component
public class TrackAdvice {
    final Logger mLogger;

    @Autowired
    public TrackAdvice(final LoggingSupport support) {
        mLogger = support.getLogger("track");
    }

    @Pointcut("@within(Monitor) || @within(org.springframework.web.bind.annotation.RestController)")
    public void methodExecution() {
    }

    @Around("methodExecution()")
    public Object invoke(final ProceedingJoinPoint point) throws Throwable {
        final long start = System.currentTimeMillis();

        boolean isOk = false;

        try {
            final Object result = point.proceed();
            isOk = true;

            return result;
        } finally {
            final long elapsed = System.currentTimeMillis() - start;
            final MethodSignature signature = (MethodSignature) point.getSignature();
            final String[] parameterNames = signature.getParameterNames();
            final StringBuilder sb = new StringBuilder();

            if (parameterNames != null && parameterNames.length != 0) {
                final Object[] parameters = point.getArgs();

                for (int i = 0; i < parameterNames.length; ++i) {
                    sb.append(' ').append(parameterNames[i]).append('=').append(parameters[i]);
                }
            }

            mLogger.info("class={} method={} {} time={}ms status={}",
                    point.getTarget().getClass().getSimpleName(),
                    point.getSignature().getName(),
                    sb.toString(),
                    elapsed,
                    isOk? "ok": "failed");
        }
    }
}
