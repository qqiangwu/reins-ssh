package zy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zy.domain.Comment;
import zy.exception.comment.CommentException;

public interface CommentService {
    Comment create(int userId, int blogId, String content) throws CommentException;

    Page<Comment> findByBlog(int blogId, Pageable page);
    Page<Comment> findByUser(int userId, Pageable page);

    Comment update(Comment comment);

    void delete(int commentId);
}
