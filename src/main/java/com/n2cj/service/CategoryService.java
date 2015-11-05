package com.n2cj.service;

import com.n2cj.entity.NewsCategory;
import com.n2cj.entity.Tag;

import java.util.List;

public interface CategoryService {
    NewsCategory getById(int cateId);

    List<NewsCategory> getAll();

    NewsCategory getByName(String name);

    List<NewsCategory> getByNames(List<String> names);
    
    List<Integer> getIdByNames(List<String> names);

    List<Tag> getHotTag(int cateId);
}
