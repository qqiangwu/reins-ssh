package zy.web.api;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import zy.domain.Blog;
import zy.domain.User;
import zy.domain.ZyUserDetails;
import zy.exception.common.ForbiddenException;
import zy.exception.user.EmailAlreadyFoundException;
import zy.exception.user.InvalidNewUserException;
import zy.exception.user.UserException;
import zy.service.BlogService;
import zy.service.UserService;
import zy.web.util.ErrorCode;
import zy.web.util.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("api/users")
public class UserResource {
    @Autowired UserService mUserService;
    @Autowired AuthenticationManager mAuthenticationManager;
    @Autowired BlogService mBlogService;

    @RequestMapping(method = RequestMethod.POST)
    public User create(final HttpServletRequest req,
                       @RequestParam final String email,
                       @RequestParam final String name,
                       @RequestParam final String password) throws UserException, ServletException {
        val user = mUserService.create(email, name, password);

        req.login(email, password);

        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User get(final Principal user) throws UserException {
        val token = (UsernamePasswordAuthenticationToken) user;
        val details = (ZyUserDetails) token.getPrincipal();

        return details.getUser();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public User setUser(@PathVariable final int id,
                        @RequestParam(required = false) final String userName,
                        @RequestParam(required = false) final byte[] image) throws ForbiddenException {
        if (SecurityUtils.getUserId() != id) {
            throw new ForbiddenException();
        }

        val result = mUserService.set(SecurityUtils.getUserId(), userName);

        if (image != null && image.length != 0) {
            mUserService.setAvatar(id, image);
        }

        return result;
    }

    @RequestMapping(value = "{id}/blogs", method = RequestMethod.GET)
    public Page<Blog> getBlogs(@PathVariable final int id,
                          @RequestParam final int page,
                          @RequestParam final int size) {
        return mBlogService.findByUser(id, new PageRequest(page, size));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidNewUserException.class)
    public ErrorCode handleInvalidInput(final InvalidNewUserException e) {
        return new ErrorCode(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyFoundException.class)
    public ErrorCode handleConflict(final EmailAlreadyFoundException e) {
        return new ErrorCode(e.getMessage());
    }
}