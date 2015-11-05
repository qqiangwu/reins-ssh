package com.n2cj.news.api;

import com.n2cj.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class AddViewApi extends ActionSupport {
    private static final Logger log = LoggerFactory.getLogger(AddViewApi.class);

    @Autowired private NewsService mNewsService;

    private Integer mNewsId;

    public void setNewsId(final int id) {
        mNewsId = id;
    }

    @Override
    public String execute() {
        log.debug("Add view for news {}", mNewsId);
        mNewsService.addViewCount(mNewsId);
        return ActionSupport.SUCCESS;
    }

    @Override
    public void validate() {
        if (mNewsId == null) {
            addFieldError("newsId", "news.emptyId");
        }
    }
}
