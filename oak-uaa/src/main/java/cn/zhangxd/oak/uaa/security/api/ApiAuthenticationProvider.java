package cn.zhangxd.oak.uaa.security.api;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * @author zhangxd
 */
public class ApiAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public boolean supports ( Class<?> authentication ) {
        return ApiAuthenticationProvider.class
            .isAssignableFrom(authentication);
    }

}
