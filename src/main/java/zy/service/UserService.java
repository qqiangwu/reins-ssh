package zy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zy.domain.User;
import zy.exception.user.UserException;

public interface UserService {
    User create(String email, String name, String password) throws UserException;

    User find(int id);
    Page<User> find(Pageable page);

    void delete(int id);

    boolean exists(int id);
}