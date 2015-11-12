package cn.edu.sjtu.reins.ssh.impl.dao;

import cn.edu.sjtu.reins.ssh.impl.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * If the code works, it was written by qqiangwu at 11:47 AM 11/12/15, otherwise I
 * don't know who wrote it.
 */
public interface UserRepo extends CrudRepository<UserEntity, String> {
}
