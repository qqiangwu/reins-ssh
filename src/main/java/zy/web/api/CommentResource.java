package zy.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zy.domain.Comment;
import zy.exception.comment.CommentException;
import zy.service.CommentService;
import zy.web.util.ErrorCode;
import zy.web.util.SecurityUtils;

@RestController
@RequestMapping("api/blogs/{blogId}/comments")
public class CommentResource {
    @Autowired CommentService mCommentService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Comment> get(@PathVariable("blogId") final int blogId,
                             @RequestParam("page") final int page,
                             @RequestParam("size") final int size) {
        return mCommentService.findByBlog(blogId, new PageRequest(page, size));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Comment post(@PathVariable("blogId") final int blogId,
                        @RequestParam("content") final String content) throws CommentException {
        return mCommentService.create(SecurityUtils.getUserId(), blogId, content);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommentException.class)
    public ErrorCode handleInvalidInput(final CommentException e) {
        return new ErrorCode(e.getMessage());
    }
}
