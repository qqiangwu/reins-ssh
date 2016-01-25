package zy.impl.tasklet;

import zy.domain.User;
import zy.support.datahub.Subscribe;
import zy.support.datahub.Subscriber;
import zy.support.track.Monitor;
import zy.web.util.SecurityUtils;

/*
 *
 * If the code works, it was written by qqiangwu at 12:47 AM 1/26/16, otherwise I
 * don't know who wrote it.
 *
 */

/**
 * Update user session info
 */
@Subscribe(topic = "user:modify")
@Monitor
public class OnUserModify implements Subscriber<User> {
    @Override
    public void onNotified(final User user) {
        SecurityUtils.getUser().setUser(user);
    }
}
