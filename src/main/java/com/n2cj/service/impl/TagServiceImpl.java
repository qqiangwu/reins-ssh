package com.n2cj.service.impl;

import com.n2cj.dao.NewsIndexPageDao;
import com.n2cj.dao.TagDao;
import com.n2cj.entity.Tag;
import com.n2cj.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {
    private static final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);
    private static final char TAGLIST_SEPARATOR = '#';

    @Autowired
    private TagDao mTagDao;
    @Autowired
    private NewsIndexPageDao mNewsIndexPageDao;

    @Override
    public Tag getTagById(final int id) {
        log.debug("Get tag by id: {}", id);
        return mTagDao.getById(id);
    }

    @Override
    public List<Tag> getTagsByIdList(final String tagNames) {
        log.debug("Get tags by names: {}", tagNames);

        final List<Tag> tags = new ArrayList<Tag>();

        if (tagNames.length() >= 2) {
            final String adjusted = tagNames.substring(1, tagNames.length() - 1);
            for (final String sid : StringUtils.split(adjusted, TAGLIST_SEPARATOR)) {
                final int id = Integer.parseInt(sid);
                tags.add(mTagDao.getById(id));
            }
        }

        return tags;
    }

    @Override
    public List<Tag> getAll() {
        log.debug("Get all tags");
        return mTagDao.getAllValid();
    }

    @Override
    public List<Tag> getSubpageList() {
        log.debug("Get Subpage tags");
        String tagNames = mNewsIndexPageDao.hotTags();
        final List<Tag> tags = new ArrayList<Tag>();

        if (tagNames.length() >= 2) {
            final String adjusted = tagNames.substring(1, tagNames.length() - 1);
            for (final String sid : StringUtils.split(adjusted, TAGLIST_SEPARATOR)) {
                final int id = Integer.parseInt(sid);
                tags.add(mTagDao.getById(id));
            }
        }

        return tags;
    }
}
