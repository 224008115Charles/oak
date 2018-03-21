package cn.zhangxd.oak.gateway.internal.config.oauth2;

import cn.zhangxd.oak.core.security.oauth2.OAuth2Properties;
import cn.zhangxd.oak.gateway.internal.security.CookieTokenExtractor;
import cn.zhangxd.oak.gateway.internal.security.OAuth2AuthenticationService;
import cn.zhangxd.oak.gateway.internal.security.OAuth2CookieHelper;
import cn.zhangxd.oak.gateway.internal.security.OAuth2TokenEndpointClient;
import cn.zhangxd.oak.gateway.internal.web.filter.RefreshTokenFilterConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Configures the RefreshFilter refreshing expired OAuth2 token Cookies.
 *
 * @author zhangxd
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class OAuth2AuthenticationConfiguration extends ResourceServerConfigurerAdapter {
    private final OAuth2Properties oAuth2Properties;
    private final OAuth2TokenEndpointClient tokenEndpointClient;
    private final TokenStore tokenStore;

    public OAuth2AuthenticationConfiguration(OAuth2Properties oAuth2Properties, OAuth2TokenEndpointClient tokenEndpointClient, TokenStore tokenStore) {
        this.oAuth2Properties = oAuth2Properties;
        this.tokenEndpointClient = tokenEndpointClient;
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.apply(refreshTokenSecurityConfigurerAdapter());
    }

    /**
     * A SecurityConfigurerAdapter to install a servlet filter that refreshes OAuth2 tokens.
     */
    private RefreshTokenFilterConfigurer refreshTokenSecurityConfigurerAdapter() {
        return new RefreshTokenFilterConfigurer(uaaAuthenticationService(), tokenStore);
    }

    @Bean
    public OAuth2CookieHelper cookieHelper() {
        return new OAuth2CookieHelper(oAuth2Properties);
    }

    @Bean
    public OAuth2AuthenticationService uaaAuthenticationService() {
        return new OAuth2AuthenticationService(tokenEndpointClient, cookieHelper());
    }

    /**
     * Configure the ResourceServer security by installing a new TokenExtractor.
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenExtractor(tokenExtractor());
    }

    /**
     * The new TokenExtractor can extract tokens from Cookies and Authorization headers.
     *
     * @return the CookieTokenExtractor bean.
     */
    @Bean
    public TokenExtractor tokenExtractor() {
        return new CookieTokenExtractor();
    }

}
