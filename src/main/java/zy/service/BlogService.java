package zy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zy.domain.Blog;
import zy.exception.blog.BlogException;

public interface BlogService {
    Blog create(int userId, String title, String content) throws BlogException;

    Blog find(int id);
    Page<Blog> find(Pageable page);
    Page<Blog> find(int userId, Pageable page);

    boolean update(int id, String title, String content);
    void delete(int id);

    boolean exists(int id);
    boolean hasAccessTo(int userId, int blogId);
}
