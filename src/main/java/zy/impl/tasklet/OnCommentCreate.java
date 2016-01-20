package zy.impl.tasklet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import zy.domain.Comment;
import zy.impl.repo.BlogRepo;
import zy.impl.repo.UserRepo;
import zy.support.datahub.Subscribe;
import zy.support.datahub.Subscriber;
import zy.support.track.Monitor;

/*
 *
 * If the code works, it was written by qqiangwu at 5:36 PM 1/20/16, otherwise I
 * don't know who wrote it.
 *
 */
@Subscribe(topic = "comment:create")
@Monitor
public class OnCommentCreate implements Subscriber<Comment> {
    @Autowired UserRepo mUserRepo;
    @Autowired BlogRepo mBlogRepo;

    @Override
    @Async
    @Transactional(readOnly = false)
    public void onNotified(final Comment comment) {
        mUserRepo.addCommentCount(comment.getUser().getId());
        mBlogRepo.addCommentCount(comment.getBlogId());
    }
}