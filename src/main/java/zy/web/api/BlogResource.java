package zy.web.api;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zy.domain.Blog;
import zy.exception.blog.BlogException;
import zy.exception.blog.InvalidUserException;
import zy.exception.user.UserException;
import zy.service.BlogService;
import zy.web.util.ErrorCode;
import zy.web.util.SecurityUtils;

@RestController
@RequestMapping("api/blogs")
public class BlogResource {
    @Autowired BlogService mBlogService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Blog> get(@RequestParam final int page,
                          @RequestParam final int size,
                          @RequestParam(defaultValue = "creationDate") String order) {
        if (!"creationDate".equals(order)) {
            order = "viewCount";
        }

        val pageReq = new PageRequest(page, size, Sort.Direction.DESC, order);

        return mBlogService.find(pageReq);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Blog getOne(@PathVariable final int id) {
        return mBlogService.find(id);
    }

    // USE POST DUE TO SERVLET API DEFECTION
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public void put(@PathVariable final int id,
                    @RequestParam final String title,
                    @RequestParam final String content) throws UserException {
        if (!mBlogService.hasAccessTo(SecurityUtils.getUser().getId(), id)) {
            throw new UserException("user has no access");
        }
        mBlogService.update(id, title, content);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable final int id) throws UserException {
        if (!mBlogService.hasAccessTo(SecurityUtils.getUser().getId(), id)) {
            throw new UserException("user has no access");
        }
        mBlogService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Blog post(@RequestParam final String title,
                     @RequestParam final String content) throws BlogException {
        return mBlogService.create(SecurityUtils.getUser().getId(), title, content);
    }

    @RequestMapping(value = "{blogId}/view", method = RequestMethod.POST)
    public void addView(@PathVariable final int blogId) {
        mBlogService.addViewCount(blogId);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUserException.class)
    public ErrorCode handleInvalidInput(final InvalidUserException e) {
        return new ErrorCode(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserException.class)
    public ErrorCode handleInvalidInput(final UserException e) {
        return new ErrorCode(e.getMessage());
    }
}
