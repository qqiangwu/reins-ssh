package com.n2cj.dao;

import com.n2cj.common.AbstractGenericDao;
import com.n2cj.entity.News;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDao extends AbstractGenericDao<News, Integer> {
    @Value("${news.itemPerPage}") private int mItemPerPage;
    @Value("${news.indexPage}") private int mIndexPageNum;

    @SuppressWarnings("unchecked")
    public List<News> getSubPageList(final int pageNum) {
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(mItemPerPage)
                .setFirstResult((pageNum - 1) * mItemPerPage)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<News> getSubPageListByCate(final int pageNum, final int cateId) {
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.like("newsCategoryList", "%#" + String.valueOf(cateId) + "#%"))
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(mItemPerPage)
                .setFirstResult((pageNum - 1) * mItemPerPage)
                .list();
    }
    
    @SuppressWarnings("unchecked")
    public List<News> getNewsListByCate(final int cateId) {
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.like("newsCategoryList", "%#" + String.valueOf(cateId) + "#%"))
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(mIndexPageNum)
                .setFirstResult(0)
                .list();
    }
    
    @SuppressWarnings({ "unchecked" })
    public List<News> getSubPageListByCateList(final int pageNum, final List<Integer> cateList) {
    	Criterion[] list = new Criterion[cateList.size()];
    	for (int i = 0; i < cateList.size(); i++) {
    		int id = cateList.get(i);
    		Criterion criterionOr = Restrictions.like("newsCategoryList", "%#" + String.valueOf(id) + "#%");
    		list[i] = criterionOr;
    	}
    	Criterion criterion = Restrictions.or(list);
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(criterion)
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(mItemPerPage)
                .setFirstResult((pageNum - 1) * mItemPerPage)
                .list();
    }
    
    @SuppressWarnings({ "unchecked" })
    public List<News> getNewsListByCateList(final List<Integer> cateList) {
    	Criterion[] list = new Criterion[cateList.size()];
    	for (int i = 0; i < cateList.size(); i++) {
    		int id = cateList.get(i);
    		Criterion criterionOr = Restrictions.like("newsCategoryList", "%#" + String.valueOf(id) + "#%");
    		list[i] = criterionOr;
    	}
    	Criterion criterion = Restrictions.or(list);
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(criterion)
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(mIndexPageNum)
                .setFirstResult(0)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<News> getSubPageListByTag(final int pageNum, final int tagId) {
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.like("newsTagList", "%#" + String.valueOf(tagId) + "#%"))
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(mItemPerPage)
                .setFirstResult((pageNum - 1) * mItemPerPage)
                .list();
    }
    
    @SuppressWarnings("unchecked")
    public List<News> getNewsListByTag(final int tagId) {
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.like("newsTagList", "%#" + String.valueOf(tagId) + "#%"))
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(mIndexPageNum)
                .setFirstResult(0)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<News> getNewsByEditor(final int pageNum, final int editorId) {
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.eq("editor.editorId", editorId))
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(mItemPerPage)
                .setFirstResult((pageNum - 1) * mItemPerPage)
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<News> getNewsByEditorFrom(final int editorId, final int start, final int n) {
        return (List<News>) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.eq("editor.editorId", editorId))
                .addOrder(Order.desc("publishtime"))
                .setMaxResults(n)
                .setFirstResult(start)
                .list();
    }

    public int getCountValid() {
        return ((Number) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }
    
    public int getCountByCateList(final List<Integer> cateList) {
    	Criterion[] list = new Criterion[cateList.size()];
    	for (int i = 0; i < cateList.size(); i++) {
    		int id = cateList.get(i);
    		Criterion criterionOr = Restrictions.like("newsCategoryList", "%#" + String.valueOf(id) + "#%");
    		list[i] = criterionOr;
    	}
    	Criterion criterion = Restrictions.or(list);
        return ((Number) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(criterion)
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    public int getCountByCate(final int cateId) {
        return ((Number) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.like("newsCategoryList", "%#" + String.valueOf(cateId) + "#%"))
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    public int getCountByTag(final int tagId) {
        return ((Number) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.like("newsTagList", "%#" + String.valueOf(tagId) + "#%"))
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    public int getCountByEditorFrom(final int editorId) {
        return ((Number) createCriteria()
                .add(Restrictions.eq("status", "normal"))
                .add(Restrictions.eq("newsIsAd", false))
                .add(Restrictions.eq("editor.editorId", editorId))
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }
}
