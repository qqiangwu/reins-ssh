package com.n2cj.service.impl;

import com.n2cj.dao.CommentDao;
import com.n2cj.dao.NewsDao;
import com.n2cj.dao.UserDao;
import com.n2cj.entity.NewsComment;
import com.n2cj.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDao mCommentDao;
    @Autowired
    private NewsDao mNewsDao;
    @Autowired
    private UserDao mUserDao;

    @Override
    public int getCommentCount() {
        log.debug("Get comment count");

        return mCommentDao.getCount();
    }

    @Override
    public int getPageCount() {
        log.debug("Get comment page");
        return mCommentDao.getCount() / COMMENTS_PER_PAGE;
    }

    /*
     * @param	pageNum starts with 0
     */
    @Override
    public List<NewsComment> getCommentInPage(int pageNum) {
        final int start = pageNum * COMMENTS_PER_PAGE;

        log.debug("Get comments in page {}[from {}]", pageNum, start);

        return mCommentDao.getNFrom(COMMENTS_PER_PAGE, start);
    }

    @Transactional(readOnly = false)
    @Override
    public void likeComment(int commentId) {

    }

    @Transactional(readOnly = false)
    @Override
    public void dislikeComment(int commentId) {

    }

    @Transactional(readOnly = false)
    @Override
    public void createComment(int newsId, int userId, String commentIp,
                              String commentText) {
        log.debug("Create comment for News[{}] from user[{}]", newsId, userId);

        final NewsComment comment = new NewsComment();

        comment.setNews(mNewsDao.getById(newsId));
        comment.setUser(mUserDao.getById(userId));
        comment.setNewsCommentIp(commentIp);
        comment.setNewsCommentText(commentText);

        mCommentDao.create(comment);
    }

    @Transactional(readOnly = false)
    @Override
    public void replyComment(int quoteId, int userId, String commentIp,
                             String commentText) {
        log.debug("Reply comment[{}] from user[{}]", quoteId, userId);

        final NewsComment comment = new NewsComment();
        final NewsComment quoted = mCommentDao.getById(quoteId);

        comment.setNews(quoted.getNews());
        comment.setNewsCommentIp(commentIp);
        comment.setNewsCommentQuote(quoteId);
        comment.setNewsCommentText(commentText);

        mCommentDao.create(comment);
    }
}
