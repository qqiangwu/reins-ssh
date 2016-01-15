package zy.impl.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import zy.impl.entity.BlogEntity;

public interface BlogRepo extends PagingAndSortingRepository<BlogEntity, Integer> {
    @Modifying
    @Query("UPDATE BlogEntity t set t.viewCount = t.viewCount + 1 WHERE t.id = ?1")
    void addViewCount(int id);

    @Modifying
    @Query("UPDATE BlogEntity t set t.commentCount = t.commentCount + 1 WHERE t.id = ?1")
    void addCommentCount(int id);

    Page<BlogEntity> findByUserOrderByCreationDateDesc(int userId, Pageable page);
}
