package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.DicWare;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DicWareDao extends AbstractGenericDao<DicWare, Integer> {

    @SuppressWarnings("unchecked")
    public DicWare getDicWareByCode(final String code) {
        Criteria criteria = createCriteria().add(Restrictions.eq("code", code))
                .setMaxResults(1);
        Object result = criteria.uniqueResult();
        if (result == null)
            return null;
        return (DicWare) result;
    }
}
