package cn.edu.sjtu.reins.ssh.service;

import cn.edu.sjtu.reins.ssh.domain.User;

import java.util.List;

/**
 * If the code works, it was written by qqiangwu at 9:02 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
public interface UserService {
    User getUserInfo(String username);
}
