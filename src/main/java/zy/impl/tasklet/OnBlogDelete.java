package zy.impl.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import zy.impl.repo.UserRepo;
import zy.support.datahub.Subscribe;
import zy.support.datahub.Subscriber;

/*
 *
 * If the code works, it was written by qqiangwu at 2:30 PM 1/20/16, otherwise I
 * don't know who wrote it.
 *
 */
@Subscribe("blog:delete")
public class OnBlogDelete implements Subscriber<Integer> {
    @Autowired UserRepo mUserRepo;

    @Override
    public void onNotified(final Integer arg) {
        mUserRepo.decrBlogCount(arg);
    }
}
