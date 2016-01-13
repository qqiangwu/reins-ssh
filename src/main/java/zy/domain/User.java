package zy.domain;

import lombok.Value;

@Value
public class User {
    int id;
    String name;
    String email;
    long createTime;
}
