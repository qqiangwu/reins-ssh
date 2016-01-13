package zy.impl.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import zy.impl.entity.CommentEntity;

public interface CommentRepo extends PagingAndSortingRepository<CommentEntity, Integer> {
    Page<CommentEntity> findByUserOrderByCreationDateDesc(int userId, Pageable page);
    Page<CommentEntity> findByBlogOrderByCreationDateDesc(int blogId, Pageable page);
}
