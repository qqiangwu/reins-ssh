package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractGenericDao<User, Integer> {
}
