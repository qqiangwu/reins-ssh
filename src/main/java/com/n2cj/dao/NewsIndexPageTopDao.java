package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.NewsIndexPageTop;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsIndexPageTopDao extends AbstractGenericDao<NewsIndexPageTop, Integer> {

    @SuppressWarnings("unchecked")
    public List<NewsIndexPageTop> topNews() {
        return createCriteria()
                .add(Restrictions.eq("id.status", "normal"))
                .addOrder(Order.asc("id.newsTopOrder"))
                .list();
    }
}
