package com.n2cj.service.impl;

import com.n2cj.dao.CategoryDao;
import com.n2cj.dao.TagDao;
import com.n2cj.entity.NewsCategory;
import com.n2cj.entity.Tag;
import com.n2cj.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao mDao;
    @Autowired
    private TagDao mTagDao;

    public NewsCategory getById(int cateId) {
        log.debug("Get category by ID : {} ", cateId);
        return mDao.getById(cateId);
    }

    public List<NewsCategory> getAll() {
        log.debug("Get all categories");
        return mDao.getAllValid();
    }

    @Override
    public NewsCategory getByName(final String name) {
        final List<NewsCategory> results = mDao.getCategoryByName(name);

        return results.isEmpty()? null: results.get(0);
    }

    @Override
    public List<NewsCategory> getByNames(List<String> names) {
        List<NewsCategory> list = new ArrayList<NewsCategory>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            List<NewsCategory> clist = mDao.getCategoryByName(name);
            if (clist.size() != 0) {
                list.add(clist.get(0));
            }
        }
        return list;
    }
    
    @Override
    public List<Integer> getIdByNames(List<String> names) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            List<NewsCategory> clist = mDao.getCategoryByName(name);
            if (clist.size() != 0) {
                list.add(clist.get(0).getNewsCategoryId());
            }
        }
        return list;
    }

    @Override
    public List<Tag> getHotTag(int cateId) {
        List<Tag> list = new ArrayList<Tag>();
        NewsCategory newsCategory = mDao.getById(cateId);
        String[] hotTags = newsCategory.getNewsCategoryHotTags().split("#");
        for (int i = 0; i < hotTags.length; i++) {
            if (!(hotTags[i]).equals("")) {
                list.add(mTagDao.getById(Integer.parseInt(hotTags[i])));
            }
        }
        return list;
    }
}
