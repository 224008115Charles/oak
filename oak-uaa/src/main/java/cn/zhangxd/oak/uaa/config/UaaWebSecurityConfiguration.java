package cn.zhangxd.oak.uaa.config;

import cn.zhangxd.oak.uaa.security.api.ApiAuthenticationProvider;
import cn.zhangxd.oak.uaa.security.sys.SysAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhangxd
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class UaaWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService sysUserDetailsService;

    private final UserDetailsService apiUserDetailsService;

    public UaaWebSecurityConfiguration(
        UserDetailsService sysUserDetailsService,
        UserDetailsService apiUserDetailsService) {
        this.sysUserDetailsService = sysUserDetailsService;
        this.apiUserDetailsService = apiUserDetailsService;
    }

    @Bean
    public AuthenticationProvider sysAuthenticationProvider() {
        SysAuthenticationProvider provider = new SysAuthenticationProvider();
        provider.setUserDetailsService(sysUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationProvider apiAuthenticationProvider() {
        ApiAuthenticationProvider provider = new ApiAuthenticationProvider();
        provider.setUserDetailsService(apiUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/swagger-ui/index.html")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(sysAuthenticationProvider());
        auth.authenticationProvider(apiAuthenticationProvider());
    }
}
