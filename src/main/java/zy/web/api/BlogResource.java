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

    @RequestMapping
    public Page<Blog> get(@PathVariable("page") final int page,
                          @PathVariable("size") final int size) {
        val pageReq = new PageRequest(page, size, Sort.Direction.DESC, "creationDate");

        return mBlogService.find(pageReq);
    }

    @RequestMapping("{id}")
    public Blog get(@PathVariable("id") final int id) {
        return mBlogService.find(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void put(@PathVariable("id") final int id,
                    @RequestParam("title") final String title,
                    @RequestParam("content") final String content) throws UserException {
        if (!mBlogService.hasAccessTo(SecurityUtils.getUser().getId(), id)) {
            throw new UserException("user has no access");
        }
        mBlogService.update(id, title, content);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void post(@RequestParam("title") final String title,
                     @RequestParam("content") final String content) throws BlogException {
        mBlogService.create(SecurityUtils.getUser().getId(), title, content);
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
