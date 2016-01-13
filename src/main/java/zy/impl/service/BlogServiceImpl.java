package zy.impl.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.domain.Blog;
import zy.exception.blog.BlogException;
import zy.exception.blog.InvalidUserException;
import zy.impl.entity.BlogEntity;
import zy.impl.repo.BlogRepo;
import zy.service.BlogService;
import zy.service.UserService;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired BlogRepo mBlogRepo;
    @Autowired UserService mUserService;

    @Override
    @Transactional
    public Blog create(final int userId, final String title, final String content) throws BlogException {
        if (!mUserService.exists(userId)) {
            throw new InvalidUserException(Integer.toString(userId));
        }

        val entity = new BlogEntity();

        entity.setUser(userId);
        entity.setTitle(title);
        entity.setContent(content);

        return fromEntity(mBlogRepo.save(entity));
    }

    @Override
    public Blog find(final int id) {
        val entity = mBlogRepo.findOne(id);

        return entity == null? null: fromEntity(entity);
    }

    @Override
    public Page<Blog> find(final Pageable page) {
        return mBlogRepo.findAll(page).map(this::fromEntity);
    }

    @Override
    public Page<Blog> find(final int userId, final Pageable page) {
        return mBlogRepo.findUser(userId, page).map(this::fromEntity);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean update(final int id, final String title, final String content) {
        val entity = mBlogRepo.findOne(id);

        if (entity != null) {
            entity.setTitle(title);
            entity.setContent(content);

            mBlogRepo.save(entity);
        }

        return entity != null;
    }

    @Override
    public void delete(final int id) {
        mBlogRepo.delete(id);
    }

    @Override
    public boolean exists(final int id) {
        return mBlogRepo.exists(id);
    }

    private final Blog fromEntity(final BlogEntity entity) {
        val user = mUserService.find(entity.getUser());

        return new Blog(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreationDate().getTime(),
                user);
    }
}
