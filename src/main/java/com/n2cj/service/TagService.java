package com.n2cj.service;

import com.n2cj.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(final int id);

    List<Tag> getTagsByIdList(String tags);

    List<Tag> getAll();

    List<Tag> getSubpageList();
}
