package cn.edu.sjtu.reins.ssh.domain;

import lombok.Value;

import java.io.Serializable;

/**
 * If the code works, it was written by qqiangwu at 3:06 PM 11/9/15, otherwise I
 * don't know who wrote it.
 */
@Value
public class Note implements Serializable {
    int id;
    String title;
    String content;
}
