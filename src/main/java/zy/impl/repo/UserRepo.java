package zy.impl.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import zy.impl.entity.UserEntity;

public interface UserRepo extends PagingAndSortingRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

    @Modifying
    @Query("UPDATE UserEntity t set t.commentCount = t.commentCount + 1 WHERE t.id = ?1")
    void addCommentCount(int id);

    @Modifying
    @Query("UPDATE UserEntity t set t.blogCount = t.blogCount + 1 WHERE t.id = ?1")
    void addBlogCount(int id);

    @Modifying
    @Query("UPDATE UserEntity t set t.commentCount = t.commentCount - 1 WHERE t.id = ?1")
    void decrCommentCount(int id);

    @Modifying
    @Query("UPDATE UserEntity t set t.blogCount = t.blogCount - 1 WHERE t.id = ?1")
    void decrBlogCount(int id);
}
