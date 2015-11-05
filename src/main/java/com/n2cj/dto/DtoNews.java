package com.n2cj.dto;

import com.n2cj.entity.News;
import com.n2cj.entity.Tag;

import java.nio.charset.StandardCharsets;
import java.util.List;

/*
 * 一般区别entity类与dto类，前者用于与数据库的通信，后者用于与前台通信
 */
public class DtoNews {
    private final News mNews;
    private final List<Tag> mTags;

    public DtoNews(final News n, final List<Tag> tags) {
        mNews = n;
        mTags = tags;
    }

    public News getRaw() {
        return mNews;
    }

    public String getTitle() {
        return mNews.getNewsTitle();
    }

    public String getContent() {
        return new String(mNews.getNewsText(), StandardCharsets.UTF_8);
    }

    public List<Tag> getTags() {
        return mTags;
    }
}
