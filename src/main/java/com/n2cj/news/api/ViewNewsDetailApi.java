package com.n2cj.news.api;

import com.n2cj.entity.News;
import com.n2cj.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/* 注意这个例子中是如何返回json对象的，参照对应的struts-news.xml */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ViewNewsDetailApi extends ActionSupport {
    private static final Logger log = LoggerFactory.getLogger(ViewNewsDetailApi.class);

    @Autowired private NewsService mNewsServ;

    private String mIds;
    private final List<DetailInfo> mResult = new ArrayList<>();

    public void setIds(final String ids) {
        mIds = ids;
    }

    @Override
    public void validate() {
        if (mIds == null) {
            addFieldError("ids", "ids.isNull");
        }
    }

    @Override
    public String execute() {
        log.info("Get News list of newsIds : ", mIds);

        final String[] ids = StringUtils.split(mIds, ',');

        for (final String sid: ids) {
            final int iid = Integer.parseInt(sid);
            final News n = mNewsServ.getNewsById(iid);

            mResult.add(new DetailInfo(n));
        }

        return ActionSupport.SUCCESS;
    }

    public List<DetailInfo> getResult() {
        return mResult;
    }

    public static final class DetailInfo {
        private final News mNews;

        public DetailInfo(final News n) {
            mNews = n;
        }

        public int getNewsId() {
            return mNews.getNewsId();
        }

        public int getNewsView() {
            return mNews.getNewsView();
        }

        public int getNewsComment() {
            return mNews.getNewsComment();
        }

        public int getNewsLike() {
            return mNews.getNewsLike();
        }
    }
}
