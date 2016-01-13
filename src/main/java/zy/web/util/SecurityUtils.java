package zy.web.util;

import org.springframework.security.core.context.SecurityContextHolder;
import zy.domain.ZyUserDetails;

public abstract class SecurityUtils {
    public static final ZyUserDetails getUser() {
        return (ZyUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    public static final int getUserId() {
        return getUser().getId();
    }
}
