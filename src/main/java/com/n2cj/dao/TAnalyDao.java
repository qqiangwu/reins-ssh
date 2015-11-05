package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.TAnaly;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class TAnalyDao extends AbstractGenericDao<TAnaly, Integer> {

    @SuppressWarnings("unchecked")
    public List<TAnaly> getTAnalyListByDate(final Date startTime,
            final Date endTime) {
        return (List<TAnaly>) createCriteria()
                //.add(Restrictions.le("editDate", endTime))
                //.add(Restrictions.gt("editDate", startTime))
                .addOrder(Order.desc("editDate")).list();
    }

}
