package com.n2cj.service;

import com.n2cj.entity.News;

import java.util.List;

public interface NewsService {
    News getNewsById(int id);

    List<News> getSubPageList(int pageNum);
    
    List<News> getSubPageNewsList(int pageNum, List<Integer>idList);

    List<News> getSubPageListByCate(int pageNum, int cateId);

    List<News> getSubPageListByTag(int pageNum, int tagId);

    List<News> getSubPageListByEditor(int pageNum, int editorId);

    List<News> getNewsByEditorInPage(final int editorId, final int pageNum);

    List<News> getNewsByTagInPage(final int tagId, final int pageNum);

    List<News> getRelatedNews(int newsId);
    
    List<News> getRelatedNewsByTag(List<Integer> tagIds);

    List<News> getRecent();
    
    //通过栏目名称得到新闻列表
    List<News> getNewsListByCateName(String cateName);
    
    //通过栏目名称列表得到新闻列表
    List<News> getNewsListByCateNameList(List<String> cateNameList);
    
    //通过标签名称得到新闻列表
    List<News> getNewsListByTagName(String tagName);
    
    //通过标签id获得新闻列表
    List<News> getNewsListByTagId(int id);

    void addViewCount(int id);

    void addLikeCount(int id);

    void addDislikeCount(int id);

    void addCommentCount(int id);

    void addForwardCount(int id);

    void addFollowCount(int id);

    int getNewsCount();
    
    int getNewsCountByCateList(List<Integer> cateList);

    int getNewsCountByCate(int cateId);

    int getNewsCountByTag(int tagId);

    int getNewsCountByEditor(int editorId);
}
