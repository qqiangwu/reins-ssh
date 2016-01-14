package zy.domain;


import lombok.ToString;

import java.util.Collections;

@ToString
public final class ZyUserDetails extends org.springframework.security.core.userdetails.User {
    private User mUser;

    public ZyUserDetails(final User user, final String password) {
        super(user.getEmail(), password, Collections.emptyList());

        mUser = user;
    }

    public int getId() {
        return mUser.getId();
    }

    public User getUser() {
        return mUser;
    }
}
