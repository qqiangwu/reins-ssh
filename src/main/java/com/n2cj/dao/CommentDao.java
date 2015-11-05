package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.NewsComment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao extends AbstractGenericDao<NewsComment, Integer> {
}
