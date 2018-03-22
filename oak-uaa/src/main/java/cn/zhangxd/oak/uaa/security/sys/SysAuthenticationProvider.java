package cn.zhangxd.oak.uaa.security.sys;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * @author zhangxd
 */
public class SysAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public boolean supports ( Class<?> authentication ) {
        return SysUsernamePasswordAuthenticationToken.class
            .isAssignableFrom(authentication);
    }

}
