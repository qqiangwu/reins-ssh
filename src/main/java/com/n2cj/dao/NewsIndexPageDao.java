package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.NewsIndexPage;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsIndexPageDao extends AbstractGenericDao<NewsIndexPage, Integer> {

    @SuppressWarnings("unchecked")
    public String hotTags() {
        List<NewsIndexPage> list = createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .list();
        if (list.size() == 0) {
            return "#";
        }
        return list.get(0).getNewsIndexHotTags();
    }
}
