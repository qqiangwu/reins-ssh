package zy.impl.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.domain.Comment;
import zy.exception.comment.CommentException;
import zy.impl.entity.CommentEntity;
import zy.impl.repo.CommentRepo;
import zy.service.BlogService;
import zy.service.CommentService;
import zy.service.UserService;
import zy.support.aop.Monitor;

@Service
@Monitor
public class CommentServiceImpl implements CommentService {
    @Autowired CommentRepo mCommentRepo;
    @Autowired UserService mUserService;
    @Autowired BlogService mBlogService;

    @Override
    @Transactional
    public Comment create(final int userId, final int blogId, final String content) throws CommentException {
        if (!mUserService.exists(userId)) {
            throw new CommentException(String.format("userId:%d", userId));
        }

        if (!mBlogService.exists(blogId)) {
            throw new CommentException(String.format("blogId:%d", blogId));
        }

        val entity = new CommentEntity();

        entity.setBlog(blogId);
        entity.setUser(userId);
        entity.setContent(content);

        return fromEntity(mCommentRepo.save(entity));
    }

    @Override
    public Page<Comment> findByBlog(final int blogId, final Pageable page) {
        return mCommentRepo.findByBlogOrderByCreationDateDesc(blogId, page).map(this::fromEntity);
    }

    @Override
    public Page<Comment> findByUser(final int userId, final Pageable page) {
        return mCommentRepo.findByUserOrderByCreationDateDesc(userId, page).map(this::fromEntity);
    }

    @Override
    public Comment update(final Comment comment) {
        val entity = new CommentEntity();

        entity.setId(comment.getId());
        entity.setContent(comment.getContent());

        return fromEntity(mCommentRepo.save(entity));
    }

    @Override
    public void delete(final int commentId) {
        mCommentRepo.delete(commentId);
    }

    private final Comment fromEntity(final CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Comment(
                entity.getId(),
                mUserService.find(entity.getUser()),
                entity.getContent(),
                entity.getCreationDate().getTime());
    }
}
