package cn.edu.sjtu.reins.ssh.domain;

import lombok.Value;

import java.util.List;

/**
 * If the code works, it was written by qqiangwu at 9:13 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
@Value
public class User {
    String username;
    String password;
    List<String> roles;
}
