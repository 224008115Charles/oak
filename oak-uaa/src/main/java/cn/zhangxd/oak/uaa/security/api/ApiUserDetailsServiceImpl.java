package cn.zhangxd.oak.uaa.security.api;

import cn.zhangxd.oak.uaa.client.SysUserService;
import cn.zhangxd.oak.uaa.client.vo.SysUserVO;
import cn.zhangxd.oak.uaa.security.UserNotActivatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 *
 * @author zhangxd
 */
@Component("apiUserDetailsService")
public class ApiUserDetailsServiceImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(ApiUserDetailsServiceImpl.class);

    private final SysUserService userService;

    public ApiUserDetailsServiceImpl(SysUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        SysUserVO user = userService.findUserByLogin(lowercaseLogin);

        if (user == null) {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
        }
        if (!user.getEnabled()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }

        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_API"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            grantedAuthorities);

    }
}
