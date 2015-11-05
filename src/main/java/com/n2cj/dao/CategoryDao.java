package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.NewsCategory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao extends AbstractGenericDao<NewsCategory, Integer> {

    @SuppressWarnings("unchecked")
    public List<NewsCategory> getAllValid() {
        return (List<NewsCategory>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .addOrder(Order.asc("newsCategoryOrder"))
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<NewsCategory> getCategoryByName(final String name) {
        return (List<NewsCategory>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsCategoryTitle", name))
                .list();
    }
}