package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.Tag;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Float on 2015/8/18.
 */
@Repository
public class TagDao extends AbstractGenericDao<Tag, Integer> {
	
    @SuppressWarnings("unchecked")
	public Tag getByName(final String name) {
    	List<Tag> list = (List<Tag>)(createCriteria()
    			.add(Restrictions.eq("tagName", name))
    			.add(Restrictions.eq("status", "normal")).list());
    	
    	if(list.size() > 0 ){
    		return list.get(0);
    	}else{
    		return null;
    	}
    }

    @SuppressWarnings("unchecked")
    public List<Tag> getAllValid() {
        return (List<Tag>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .list();
    }
}
