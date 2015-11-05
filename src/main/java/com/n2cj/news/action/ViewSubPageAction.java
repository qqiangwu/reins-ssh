package com.n2cj.news.action;

import com.n2cj.entity.News;
import com.n2cj.entity.Tag;
import com.n2cj.service.CategoryService;
import com.n2cj.service.NewsService;
import com.n2cj.service.TagService;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ViewSubPageAction extends ActionSupport {
    private static final Logger log = LoggerFactory.getLogger(ViewSubPageAction.class);

    @Value("${news.itemPerPage}") private int mItemPerPage;
    @Autowired private NewsService mNewsServ;
    @Autowired private TagService mTagServ;
    @Autowired private CategoryService mCategoryServ;

    private Integer mPageNum;
    private ResultProxy mResult;

    public void setPageNum(final Integer pageNum) {
        mPageNum = pageNum;
    }

    @Override
    public String execute() {
        if (mPageNum == null) {
            mPageNum = 1;
        }

        log.info("Get SubPage News of page number = {}", mPageNum);

        final List<News> news = mNewsServ.getSubPageList(mPageNum);

        if (mPageNum == 1 || !news.isEmpty()) {
            final List<Tag> tags = mTagServ.getSubpageList();
            final int count = mNewsServ.getNewsCount();
            mResult = new ResultProxy(mItemPerPage, mPageNum, news, tags, count);
            return ActionSupport.SUCCESS;
        } else {
            return ActionSupport.NONE;
        }
    }

    public ResultProxy getResult() {
        return mResult;
    }

    public static final class ResultProxy {
        private final List<News> mNews;
        private final List<Tag> mTags;
        private final int mTotalNewsCount;
        private final int mPageNum;

        private int mTotalPages;
        private int mStartPage;
        private int mEndPage;

        public ResultProxy(final int itemPerPage, final int pageNum, final List<News> news, final List<Tag> tags, final int newsCount) {
            mNews = news;
            mTags = tags;
            mPageNum = pageNum;
            mTotalNewsCount = newsCount;

            if (itemPerPage == 1) {
                mTotalPages = mTotalNewsCount;
            } else {
                mTotalPages = 1 + mTotalNewsCount / itemPerPage;
            }
            if(mTotalPages > 10){
                mTotalPages = 10;
            }
            int pageSize = 4;
            int PageNum_2 = (int) (pageSize % 2 == 0 ? Math.ceil(pageSize / 2) + 1 : Math.ceil(pageSize / 2));
            int PageNum_3 = (int) (pageSize % 2 == 0 ? Math.ceil(pageSize / 2) : Math.ceil(pageSize / 2) + 1);
            if (pageSize >= mTotalPages) {
                mStartPage = 0;
                mEndPage = mTotalPages - 1;
            } else if (mPageNum < PageNum_2) {
                mStartPage = 0;
                mEndPage = mTotalPages - 1 > pageSize ? pageSize : mTotalPages - 1;
            } else {
                mStartPage = mPageNum + PageNum_3 >= mTotalPages ? mTotalPages - pageSize - 1 : mPageNum - PageNum_2 + 1;
                int t = mStartPage + pageSize;
                mEndPage = t > mTotalPages ? mTotalPages - 1 : t;
            }
            if (mStartPage < 0) {
                mStartPage = 0;
                mEndPage = pageSize;
            }
            if (mEndPage < 0) {
                mEndPage = 0;
            }
        }

        public List<News> news() {
            return mNews;
        }

        public List<Tag> tags() {
            return mTags;
        }

        public int newsCount() {
            return mTotalNewsCount;
        }

        public int pageCount() {
            return mTotalPages;
        }

        public int currentPage() {
            return mPageNum;
        }

        public int startPage() {
            return mStartPage;
        }

        public int endPage() {
            return mEndPage;
        }
    }
}
