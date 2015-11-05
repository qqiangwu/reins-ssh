package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.TagNewsRel;
import com.n2cj.entity.TagNewsRelId;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagNewsRelDao extends AbstractGenericDao<TagNewsRelId, Integer> {
    @SuppressWarnings("unchecked")
    public List<TagNewsRel> getByTagFrom(final int tagId, final int start, final int n) {
        return createCriteria()
                .add(Restrictions.eq("id.tagId", tagId))
                .addOrder(Order.desc("id.addtime"))
                .setFirstResult(start)
                .setMaxResults(n)
                .list();
    }
}
