package zy.impl.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import zy.impl.entity.UserEntity;

public interface UserRepo extends PagingAndSortingRepository<UserEntity, Integer> {
    boolean emailEExists(String email);
}
