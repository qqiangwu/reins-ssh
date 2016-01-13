package zy.domain;

import lombok.Value;

@Value
public class Comment {
    int id;
    User user;
    String content;
    long createTime;
}
