package com.n2cj.service;

import com.n2cj.entity.NewsComment;

import java.util.List;

public interface CommentService {
    public static final int COMMENTS_PER_PAGE = 10;

    int getCommentCount();

    int getPageCount();

    List<NewsComment> getCommentInPage(int pageNum);

    void likeComment(int commentId);

    void dislikeComment(int commentId);

    void createComment(int newsId, int userId, String commentIp, String commentText);

    void replyComment(int quoteId, int userId, String commentIp, String commentText);
}
