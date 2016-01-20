package zy.impl.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import zy.domain.Blog;
import zy.impl.repo.UserRepo;
import zy.support.datahub.Subscribe;
import zy.support.datahub.Subscriber;
import zy.support.track.Monitor;

/*
 *
 * If the code works, it was written by qqiangwu at 2:30 PM 1/20/16, otherwise I
 * don't know who wrote it.
 *
 */
@Subscribe(topic = "blog:delete")
@Monitor
public class OnBlogDelete implements Subscriber<Blog> {
    @Autowired UserRepo mUserRepo;

    @Override
    @Async
    @Transactional(readOnly = false)
    public void onNotified(final Blog blog) {
        mUserRepo.decrBlogCount(blog.getUser().getId());
    }
}
