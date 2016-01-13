package zy.support.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j(topic = "GaugeCollector")
public class GaugeCollector {
    final Map<Object, Method> mGauges = new HashMap<>();

    @Autowired
    public GaugeCollector(final ApplicationContext context) {
        for (final Map.Entry<String, Object> entry: context.getBeansWithAnnotation(Gauge.class).entrySet()) {
            final Object gauge = entry.getValue();

            try {
                final Method method = gauge.getClass().getMethod("collect");
            } catch (NoSuchMethodException e) {
                log.error("Gauge {} has no method collect()", entry.getKey());
            }
        }
    }

    public void collect() {
        for (final Map.Entry<Object, Method> entry: mGauges.entrySet()) {
            try {
                entry.getValue().invoke(entry.getKey());
            } catch (IllegalAccessException|InvocationTargetException e) {
                log.error("Gauge collect failed", e);
            }
        }
    }
}
