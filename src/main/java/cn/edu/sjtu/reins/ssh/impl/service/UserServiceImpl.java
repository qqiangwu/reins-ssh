package cn.edu.sjtu.reins.ssh.impl.service;

import cn.edu.sjtu.reins.ssh.domain.User;
import cn.edu.sjtu.reins.ssh.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * If the code works, it was written by qqiangwu at 9:02 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
@Service
@Transactional(readOnly = true)
@Cacheable
@CacheConfig(cacheNames = "reins:user")
public class UserServiceImpl implements UserService {

    @Override
    public User getUserInfo(final String username) {
        return null;
    }
}
