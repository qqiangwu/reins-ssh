package com.n2cj.service.impl;

import com.n2cj.dao.CategoryDao;
import com.n2cj.dao.NewsDao;
import com.n2cj.dao.TagDao;
import com.n2cj.dao.TagNewsRelDao;
import com.n2cj.entity.News;
import com.n2cj.entity.NewsCategory;
import com.n2cj.entity.Tag;
import com.n2cj.entity.TagNewsRel;
import com.n2cj.service.NewsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;

@Service
@Transactional(readOnly = true) /* 默认用只读的事务 */
@CacheConfig(cacheNames = "cache.news")
public final class NewsServiceImpl implements NewsService {
    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
    private static final char TAGLIST_SEPARATOR = '#';

    @Value("${news.related}") private int mNumberOfRelated;
    @Value("${news.itemPerPage}") private int mItemPerPage;

    @Autowired private NewsDao mNewsDao;
    @Autowired private TagNewsRelDao mTagNewsRelDao;
    @Autowired private TagDao mTagDao;
    @Autowired private CategoryDao mCategoryDao;

    @PostConstruct
    public void setup() {
        // DO SOME INITIALIZATION TASKS HERE
    }

    @PreDestroy
    public void destroy() {
        // do some finalization tasks here
    }

    @Override
    @Cacheable
    public News getNewsById(final int id) {
        log.debug("Request for news[{}]", id);

        final News n = mNewsDao.getById(id);

        if (n == null) {
            log.debug("Cannot found news [{}]", id);
        }

        return n;
    }

    @Override
    @Cacheable(key = "'editor-page:' + #editorId + '.' + #pageNum")
    public List<News> getNewsByEditorInPage(final int editorId, final int pageNum) {
        log.debug("Get news by editor[{}] in page {}", editorId, pageNum);

        final int start = pageNum * mItemPerPage;
        final int n = mItemPerPage;

        return mNewsDao.getNewsByEditorFrom(editorId, start, n);
    }

    @Override
    @Cacheable(key = "'tag-page:' + #tagId + '.' + #pageNum")
    public List<News> getNewsByTagInPage(final int tagId, final int pageNum) {
        log.debug("Get news by tag {} in page {}", tagId, pageNum);

        final int start = pageNum * mItemPerPage;
        final int n = mItemPerPage;

        final List<TagNewsRel> rels = mTagNewsRelDao.getByTagFrom(tagId, start, n);
        final List<News> result = new ArrayList<News>();

        for (final TagNewsRel rel : rels) {
            result.add(rel.getNews());
        }

        return result;
    }

    @Override
    @Cacheable(key = "'subpage:' + #pageNum")
    public List<News> getSubPageList(int pageNum) {
        log.debug("Request for news of SubPage [{}]", pageNum);

        final List<News> n = mNewsDao.getSubPageList(pageNum);

        if (n == null) {
            log.debug("Cannot found news of SubPage [{}]", pageNum);
        }

        return n;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getSubPageNewsList(int pageNum, List<Integer>idList) {
        log.debug("Request for news of SubPage [{}]", pageNum);

        final List<News> n = mNewsDao.getSubPageListByCateList(pageNum, idList);

        if (n == null) {
            log.debug("Cannot found news of SubPage [{}]", pageNum);
        }

        return n;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getSubPageListByCate(int pageNum, int cateId) {
        log.debug("Request for news of SubPage by Category [{}]", pageNum, cateId);

        final List<News> n = mNewsDao.getSubPageListByCate(pageNum, cateId);

        if (n == null) {
            log.debug("Cannot found news of SubPage by Category [{}]", pageNum, cateId);
        }

        return n;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getSubPageListByTag(int pageNum, int tagId) {
        log.debug("Request for news of SubPage by Tag [{}]", pageNum, tagId);

        final List<News> n = mNewsDao.getSubPageListByTag(pageNum, tagId);

        if (n == null) {
            log.debug("Cannot found news of SubPage by Tag [{}]", pageNum, tagId);
        }

        return n;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getSubPageListByEditor(int pageNum, int editorId) {
        log.debug("Request for news of SubPage by Editor [{}]", pageNum, editorId);

        final List<News> n = mNewsDao.getNewsByEditor(pageNum, editorId);

        if (n == null) {
            log.debug("Cannot found news of SubPage by Editor [{}]", pageNum, editorId);
        }

        return n;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getRelatedNews(int newsId) {
        log.debug("Request for news of Related News [{}]", newsId);

        News news = mNewsDao.getById(newsId);
        String tagNames = news.getNewsTagList();
        List<News> newslist = new ArrayList<News>();
        List<News> result = new ArrayList<News>();
        Set<Integer> idSet = new HashSet<Integer>();
        idSet.add(newsId);

        if (tagNames.length() >= 2) {
            final String adjusted = tagNames.substring(1, tagNames.length() - 1);
            for (final String sid : StringUtils.split(adjusted, TAGLIST_SEPARATOR)) {
                final int id = Integer.parseInt(sid);
                List<News> n = mNewsDao.getSubPageListByTag(1, id);
                newslist.addAll(n);
            }
        }

        Comparator<News> comparator = new Comparator<News>() {
            public int compare(News n1, News n2) {
                if (n2.getAddtime().after(n1.getAddtime()))
                    return 1;
                else if (n1.getAddtime().after(n2.getAddtime()))
                    return -1;
                else
                    return 0;
            }
        };
        Collections.sort(newslist, comparator);
        int count = 0;
        for (int i = 0; i < newslist.size(); i++) {
            News n = newslist.get(i);
            if (!idSet.contains(n.getNewsId())) {
                idSet.add(n.getNewsId());
                result.add(n);
                count++;
                if (count >= 6) {
                    break;
                }
            }
        }
        return result;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getNewsListByCateName(String cateName) {
        log.debug("Request for news by Category name {}", cateName);
        
        int cateId = -1;
        List<NewsCategory> cates =  mCategoryDao.getCategoryByName(cateName);
        if(cates.size() != 0){
        	cateId = cates.get(0).getNewsCategoryId();
        }

        final List<News> n = mNewsDao.getNewsListByCate(cateId);

        if (n == null) {
            log.debug("Cannot found news by Category name {}", cateName);
        }

        return n;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getNewsListByCateNameList(List<String> cateNameList) {
        log.debug("Request for news by Category name list");
        
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < cateNameList.size(); i++) {
            String name = cateNameList.get(i);
            List<NewsCategory> clist = mCategoryDao.getCategoryByName(name);
            if (clist.size() != 0) {
                list.add(clist.get(0).getNewsCategoryId());
            }
        }

        final List<News> n = mNewsDao.getNewsListByCateList(list);

        if (n == null) {
            log.debug("Cannot found news by Category name list");
        }

        return n;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getNewsListByTagName(String tagName) {
        log.debug("Request for news by Tag name {}", tagName);
        
        int tagId = -1;
        Tag tag = mTagDao.getByName(tagName);
        
        if(tag != null){
        	tagId = tag.getTagId();
        }

        final List<News> n = mNewsDao.getNewsListByTag(tagId);

        if (n == null) {
            log.debug("Cannot found news by Tag name {}", tagName);
        }

        return n;
    }

    // I don't want to add more @Cacheable, but you should!
    @Override
    public List<News> getNewsListByTagId(int id){
    	final List<News> n = mNewsDao.getNewsListByTag(id);

    	if (n == null) {
            log.debug("Cannot found news by Tag name {}", id);
        }

        return n;
    }

    @CacheEvict(allEntries = true) // Invalidate all cache
    @Transactional(readOnly = false)
    @Override
    public void addViewCount(final int newsId) {
        log.debug("Add view count for news {}", newsId);

        final News news = mNewsDao.getById(newsId);

        if (news != null) {
            news.setNewsView(news.getNewsView() + 1);
            mNewsDao.update(news);
        } else {
            log.debug("Cannot find news");
        }
    }

    @CacheEvict(allEntries = true) // Invalidate all cache
    @Transactional(readOnly = false)
    @Override
    public void addLikeCount(final int newsId) {
        log.debug("Add like count for news {}", newsId);

        final News news = mNewsDao.getById(newsId);

        if (news != null) {
            news.setNewsLike(news.getNewsLike() + 1);
            mNewsDao.update(news);
        } else {
            log.debug("Cannot find news");
        }
    }

    @CacheEvict(allEntries = true) // Invalidate all cache
    @Transactional(readOnly = false)
    @Override
    public void addDislikeCount(final int newsId) {
        log.debug("Add dislike count for news {}", newsId);

        final News news = mNewsDao.getById(newsId);

        if (news != null) {
            news.setNewsDislike(news.getNewsDislike() + 1);
            mNewsDao.update(news);
        } else {
            log.debug("Cannot find news");
        }
    }

    @CacheEvict(allEntries = true) // Invalidate all cache
    @Transactional(readOnly = false)
    @Override
    public void addCommentCount(final int newsId) {
        log.debug("Add comment count for news {}", newsId);

        final News news = mNewsDao.getById(newsId);

        if (news != null) {
            news.setNewsComment(1 + news.getNewsComment());
            mNewsDao.update(news);
        } else {
            log.debug("Cannot find news");
        }
    }

    @CacheEvict(allEntries = true) // Invalidate all cache
    @Transactional(readOnly = false)
    @Override
    public void addForwardCount(final int newsId) {
        log.debug("Add forward count for news {}", newsId);

        final News news = mNewsDao.getById(newsId);

        if (news != null) {
            news.setNewsForward(1 + news.getNewsForward());
            mNewsDao.update(news);
        } else {
            log.debug("Cannot find news");
        }
    }

    @CacheEvict(allEntries = true) // Invalidate all cache
    @Transactional(readOnly = false)
    @Override
    public void addFollowCount(final int newsId) {
        log.debug("Add follow count for news {}", newsId);

        final News news = mNewsDao.getById(newsId);

        if (news != null) {
            news.setNewsFollow(1 + news.getNewsFollow());
            mNewsDao.update(news);
        } else {
            log.debug("Cannot find news");
        }
    }

    @Override
    public int getNewsCount() {
        log.debug("Get News count{}");
        return mNewsDao.getCountValid();
    }
    
    @Override
    public int getNewsCountByCateList(List<Integer> cateList) {
        log.debug("Get News count by CateList");
        return mNewsDao.getCountByCateList(cateList);
    }

    @Override
    public int getNewsCountByCate(int cateId) {
        log.debug("Get News count by Cate{}", cateId);
        return mNewsDao.getCountByCate(cateId);
    }

    @Override
    public int getNewsCountByTag(int tagId) {
        log.debug("Get News count by Tag{}", tagId);
        return mNewsDao.getCountByTag(tagId);
    }

    @Override
    public int getNewsCountByEditor(int editorId) {
        log.debug("Get News count by Editor{}", editorId);
        return mNewsDao.getCountByEditorFrom(editorId);
    }

	@Override
	public List<News> getRelatedNewsByTag(List<Integer> tagIds) {
		log.debug("Request for news of Related News by Tag List");

        List<News> newslist = new ArrayList<News>();
        List<News> result = new ArrayList<News>();
        Set<Integer> idSet = new HashSet<Integer>();


        for (int i = 0; i<tagIds.size();i++) {
            final int id = tagIds.get(i);
            List<News> n = mNewsDao.getSubPageListByTag(1, id);
            newslist.addAll(n);
        }

        Comparator<News> comparator = new Comparator<News>() {
            public int compare(News n1, News n2) {
                if (n2.getAddtime().after(n1.getAddtime()))
                    return 1;
                else if (n1.getAddtime().after(n2.getAddtime()))
                    return -1;
                else
                    return 0;
            }
        };
        Collections.sort(newslist, comparator);
        int count = 0;
        for (int i = 0; i < newslist.size(); i++) {
            News n = newslist.get(i);
            if (!idSet.contains(n.getNewsId())) {
                idSet.add(n.getNewsId());
                result.add(n);
                count++;
                if (count >= 6) {
                    break;
                }
            }
        }
        return result;
	}

    @Override
    public List<News> getRecent() {
        final List<News> related = getSubPageList(0);
        final int max = related.size();

        return related.subList(0, Math.min(max, mNumberOfRelated) - 1);
    }
}
