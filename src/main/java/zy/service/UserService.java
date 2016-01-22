package zy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import zy.domain.User;
import zy.exception.user.UserException;

public interface UserService extends UserDetailsService {
    User create(String email, String name, String password) throws UserException;

    User find(int id);

    Page<User> find(Pageable page);

    User set(int id, String name);

    void delete(int id);

    boolean exists(int id);

    void setAvatar(int id, byte[] image);
}
