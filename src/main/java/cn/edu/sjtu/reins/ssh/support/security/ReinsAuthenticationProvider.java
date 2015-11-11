package cn.edu.sjtu.reins.ssh.support.security;

import cn.edu.sjtu.reins.ssh.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * If the code works, it was written by qqiangwu at 8:49 PM 11/11/15, otherwise I
 * don't know who wrote it.
 */
@Component
public final class ReinsAuthenticationProvider implements UserDetailsService {
    @Autowired private UserService mUserService;

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        val user = mUserService.getUserInfo(s);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("cannot find user %s", s));
        }

        return new User(user.getUsername(), user.getPassword(),
                true, true, true, true,
                toAuthorities(user.getRoles()));
    }

    private Set<GrantedAuthority> toAuthorities(final List<String> roles) {
        val result = new HashSet<GrantedAuthority>();

        for (final String role: roles) {
            result.add(new SimpleGrantedAuthority(role));
        }

        return result;
    }
}
