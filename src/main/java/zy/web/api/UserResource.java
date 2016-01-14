package zy.web.api;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import zy.domain.User;
import zy.domain.ZyUserDetails;
import zy.exception.user.EmailAlreadyFoundException;
import zy.exception.user.InvalidNewUserException;
import zy.exception.user.UserException;
import zy.service.UserService;
import zy.web.util.ErrorCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("api/users")
public class UserResource {
    @Autowired UserService mUserService;
    @Autowired AuthenticationManager mAuthenticationManager;

    @RequestMapping(method = RequestMethod.POST)
    public User create(final HttpServletRequest req,
                       @RequestParam("email") final String email,
                       @RequestParam("name") final String name,
                       @RequestParam("password") final String password) throws UserException, ServletException {
        mUserService.create(email, name, password);

        req.logout();
        req.login(email, password);

        return ((ZyUserDetails)mUserService.loadUserByUsername(email)).getUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    public User get(final Principal user) throws UserException {
        val token = (UsernamePasswordAuthenticationToken) user;
        val details = (ZyUserDetails) token.getPrincipal();

        return details.getUser();
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

    private final User loginAfterRegistration(final HttpServletRequest req, final String email) {
        val details = mUserService.loadUserByUsername(email);

        val token = new UsernamePasswordAuthenticationToken(
                details,
                details.getPassword(),
                details.getAuthorities());
        mAuthenticationManager.authenticate(token);

        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        return ((ZyUserDetails) details).getUser();
    }
}