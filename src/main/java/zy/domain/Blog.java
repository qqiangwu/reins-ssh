package zy.domain;

import lombok.Value;

@Value
public class Blog {
    int id;
    String title;
    String content;
    long createTime;
    int viewCount;
    int commentCount;
    User user;
}
