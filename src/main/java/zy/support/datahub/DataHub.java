package zy.support.datahub;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;

/*
 *
 * If the code works, it was written by qqiangwu at 10:09 AM 1/20/16, otherwise I
 * don't know who wrote it.
 *
 */
@Component
public class DataHub {
    private final MultiValueMap<String, Subscriber> mSubscribers = new LinkedMultiValueMap<>();
    private final ApplicationContext mContext;

    @Autowired
    public DataHub(final ApplicationContext context) {
        mContext = context;
    }

    @PostConstruct
    public void setup() {
        val candidates = mContext.getBeansWithAnnotation(Subscribe.class);

        for (val bean: candidates.values()) {
            if (bean instanceof Subscriber) {
                val ann = ((Subscriber) bean).getClass().getAnnotation(Subscribe.class);

                mSubscribers.add(ann.value(), (Subscriber) bean);
            }
        }
    }

    public void notify(final String topic, final Object arg) {
        val subscribers = mSubscribers.get(topic);

        if (subscribers != null) {
            subscribers.forEach(x -> x.onNotified(arg));
        }
    }
}
