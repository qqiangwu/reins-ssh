package com.n2cj.news.action;

import com.n2cj.entity.News;
import com.n2cj.entity.Tag;
import com.n2cj.service.NewsService;
import com.n2cj.service.TagService;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ViewNewsAction extends ActionSupport {
    private static final Logger log = LoggerFactory.getLogger(ViewNewsAction.class);

    @Autowired private NewsService mNewsService;
    @Autowired private TagService mTagService;

    private Integer mNewsId;
    private ResultProxy mResult;

    /* 参数在这里传入 */
    public void setNewsId(final Integer id) {
        mNewsId = id;
    }

    /* 检查参数合法性 */
    @Override
    public void validate() {
        if (mNewsId == null) {
            addFieldError("NewsId", "news.badid");
        }
    }

    @Override
    public String execute() {
        log.info("Get News of id = {}", mNewsId);

        final News news = mNewsService.getNewsById(mNewsId);

        if (news != null) {
            final List<Tag> tags = mTagService.getTagsByIdList(news.getNewsTagList());
            final List<News> relatedNews = mNewsService.getRelatedNews(mNewsId);

            mResult = new ResultProxy(news, tags, relatedNews);

            return ActionSupport.SUCCESS;
        } else {
            return ActionSupport.NONE;
        }
    }

    /* 所有getXXX都会生成一个ＸＸＸ对象，用于jsp的渲染　*/
    public ResultProxy getResult() {
        return mResult;
    }

    public static final class ResultProxy {
        private final News mNews;
        private final List<Tag> mTags;
        private final List<News> mNewslist;

        public ResultProxy(final News n, final List<Tag> tags, final List<News> related) {
            mNews = n;
            mNewslist = related;
            mTags = tags;
        }

        public List<News> relatedNews() {
            return mNewslist;
        }

        public News news() {
            return mNews;
        }

        public String content() {
            return new String(mNews.getNewsText(), StandardCharsets.UTF_8);
        }

        public List<Tag> tags() {
            return mTags;
        }
    }
}
