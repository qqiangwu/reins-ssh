package zy.domain;

import lombok.Value;
import zy.impl.entity.UserEntity;

@Value
public class User {
    int id;
    String name;
    String email;
    long createTime;
    long lastAccess;
    int blogCount;
    int commentCount;

    public static final User fromEntity(final UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCreationDate().getTime(),
                entity.getLastAccessDate().getTime(),
                entity.getBlogCount(),
                entity.getCommentCount());
    }
}
