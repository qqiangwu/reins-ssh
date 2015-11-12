package cn.edu.sjtu.reins.ssh.impl.dao;

import cn.edu.sjtu.reins.ssh.impl.entity.UserRoleRelEntity;
import cn.edu.sjtu.reins.ssh.impl.entity.UserRoleRelEntityPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * If the code works, it was written by qqiangwu at 11:48 AM 11/12/15, otherwise I
 * don't know who wrote it.
 */
public interface UserRoleRepo extends CrudRepository<UserRoleRelEntity, UserRoleRelEntityPK> {
    @Query("select u.role from UserRoleRelEntity u where u.id = ?1")
    List<String> findById(final String username);
}
