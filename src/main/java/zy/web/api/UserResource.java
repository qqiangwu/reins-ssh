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
import zy.web.util.SecurityUtils;

@RestController
@RequestMapping("api/users")
public class UserResource {
    @Autowired UserService mUserService;
    @Autowired AuthenticationManager mAuthenticationManager;

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestParam("email") final String email,
                       @RequestParam("name") final String name,
                       @RequestParam("password") final String password) throws UserException {
        mUserService.create(email, name, password);

        return loginAfterRegistration(email);
    }

    @RequestMapping(method = RequestMethod.GET)
    public User get() throws UserException {
        return SecurityUtils.getUser().getUser();
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

    private final User loginAfterRegistration(final String email) {
        val details = mUserService.loadUserByUsername(email);
        val auth = new UsernamePasswordAuthenticationToken(
                details,
                details.getPassword(),
                details.getAuthorities());
        mAuthenticationManager.authenticate(auth);

        if (auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        return ((ZyUserDetails) details).getUser();
    }
}