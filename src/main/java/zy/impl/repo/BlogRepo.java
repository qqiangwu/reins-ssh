package zy.impl.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import zy.impl.entity.BlogEntity;

public interface BlogRepo extends PagingAndSortingRepository<BlogEntity, Integer> {
    Page<BlogEntity> findByUserOrderByCreationDateDesc(int userId, Pageable page);
}
