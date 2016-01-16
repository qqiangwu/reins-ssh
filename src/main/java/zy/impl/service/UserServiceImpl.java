package zy.impl.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.domain.User;
import zy.domain.ZyUserDetails;
import zy.exception.user.EmailAlreadyFoundException;
import zy.exception.user.InvalidNewUserException;
import zy.exception.user.UserException;
import zy.impl.entity.UserEntity;
import zy.impl.repo.UserRepo;
import zy.service.UserService;
import zy.support.aop.Monitor;

import java.sql.Timestamp;
import java.util.Date;

@Service
@Monitor
public class UserServiceImpl implements UserService {
    @Autowired UserRepo mUserRepo;

    @Override
    @Transactional
    public User create(final String email, final String name, final String password) throws UserException {
        if (email.length() > 64) {
            throw new InvalidNewUserException(email);
        }

        if (name.length() > 32) {
            throw new InvalidNewUserException(name);
        }

        if (password.length() > 32) {
            throw new InvalidNewUserException(password);
        }

        if (mUserRepo.findByEmail(email) != null) {
            throw new EmailAlreadyFoundException(email);
        }

        val user = new UserEntity();

        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setEnabled((byte) 1);

        val now = new Timestamp(new Date().getTime());
        user.setCreationDate(now);
        user.setLastAccessDate(now);

        return fromEntity(mUserRepo.save(user));
    }

    @Override
    public User find(final int id) {
        val entity = mUserRepo.findOne(id);

        return entity == null? null: fromEntity(entity);
    }

    @Override
    public Page<User> find(Pageable page) {
        return mUserRepo.findAll(page).map(this::fromEntity);
    }

    @Override
    public void delete(final int id) {
        mUserRepo.delete(id);
    }

    @Override
    public boolean exists(int id) {
        return mUserRepo.exists(id);
    }

    private final User fromEntity(final UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreationDate().getTime());
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        val entity = mUserRepo.findByEmail(email);

        return new ZyUserDetails(fromEntity(entity), entity.getPassword());
    }
}
