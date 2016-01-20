package zy.domain;

import lombok.Value;

@Value
public class Comment {
    int id;
    int blogId;
    User user;
    String content;
    long createTime;
}
