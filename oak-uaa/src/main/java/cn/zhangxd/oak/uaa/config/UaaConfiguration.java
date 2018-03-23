package cn.zhangxd.oak.uaa.config;

import cn.zhangxd.oak.core.config.OakProperties;
import cn.zhangxd.oak.core.security.AuthoritiesConstants;
import cn.zhangxd.oak.uaa.security.UaaUsernamePasswordTokenGranter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhangxd
 */
@Configuration
@EnableAuthorizationServer
public class UaaConfiguration extends AuthorizationServerConfigurerAdapter implements ApplicationContextAware {
    /**
     * Access tokens will not expire any earlier than this.
     */
    private static final int MIN_ACCESS_TOKEN_VALIDITY_SECS = 60;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @EnableResourceServer
    public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        private final TokenStore tokenStore;

        public ResourceServerConfiguration(TokenStore tokenStore) {
            this.tokenStore = tokenStore;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                .exceptionHandling()
                    .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                    .antMatchers("/management/health").permitAll()
                    .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
                    .antMatchers("/v2/api-docs/**").permitAll()
                    .antMatchers("/swagger-resources/configuration/ui").permitAll()
                    .antMatchers("/swagger-ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
            ;
            // @formatter:on
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId("oak-uaa").tokenStore(tokenStore);
        }

        @Bean
        @Qualifier("loadBalancedRestTemplate")
        public RestTemplate loadBalancedRestTemplate(RestTemplateCustomizer customizer) {
            RestTemplate restTemplate = new RestTemplate();
            customizer.customize(restTemplate);
            return restTemplate;
        }

        @Bean
        @Qualifier("vanillaRestTemplate")
        public RestTemplate vanillaRestTemplate() {
            return new RestTemplate();
        }
    }

    private final OakProperties oakProperties;

    private final UaaProperties uaaProperties;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    public UaaConfiguration(
        AuthenticationManager authenticationManager,
        UserDetailsService userDetailsService,
        OakProperties oakProperties,
        UaaProperties uaaProperties) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.oakProperties = oakProperties;
        this.uaaProperties = uaaProperties;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        int accessTokenValidity = uaaProperties.getWebClientConfiguration().getAccessTokenValidityInSeconds();
        accessTokenValidity = Math.max(accessTokenValidity, MIN_ACCESS_TOKEN_VALIDITY_SECS);
        int refreshTokenValidity = uaaProperties.getWebClientConfiguration().getRefreshTokenValidityInSecondsForRememberMe();
        refreshTokenValidity = Math.max(refreshTokenValidity, accessTokenValidity);
        // @formatter:off
        clients.inMemory()
            .withClient(uaaProperties.getWebClientConfiguration().getClientId())
            .secret(uaaProperties.getWebClientConfiguration().getSecret())
            .scopes("api")
            .autoApprove(true)
            .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
            .accessTokenValiditySeconds(accessTokenValidity)
            .refreshTokenValiditySeconds(refreshTokenValidity)
        .and()
            .withClient(oakProperties.getSecurity().getClientAuthorization().getClientId())
            .secret(oakProperties.getSecurity().getClientAuthorization().getClientSecret())
            .scopes("web-app")
            .autoApprove(true)
            .authorizedGrantTypes("client_credentials")
            .accessTokenValiditySeconds((int) oakProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds())
            .refreshTokenValiditySeconds((int) oakProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe())
        ;
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //pick up all  TokenEnhancers incl. those defined in the application
        //this avoids changes to this class if an application wants to add its own to the chain
        Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));
        endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .tokenStore(tokenStore())
            .tokenEnhancer(tokenEnhancerChain)
            //don't reuse or we will run into session inactivity timeouts
            .reuseRefreshTokens(false)
        ;

        endpoints.tokenGranter(uaaTokenGranter(endpoints.getTokenServices(), endpoints.getAuthorizationCodeServices(),
            endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
    }

    private TokenGranter uaaTokenGranter(
        AuthorizationServerTokenServices tokenServices, AuthorizationCodeServices codeServices,
        ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory
    ) {
        List<TokenGranter> tokenGranters = new ArrayList<>();
        tokenGranters.add(new UaaUsernamePasswordTokenGranter(authenticationManager,
            tokenServices, clientDetailsService, requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices,
            clientDetailsService, requestFactory));
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices,
            codeServices, clientDetailsService, requestFactory));
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices,
            clientDetailsService, requestFactory));
        tokenGranters.add(new ImplicitTokenGranter(tokenServices,
            clientDetailsService, requestFactory));
        return new CompositeTokenGranter(tokenGranters);
    }

    /**
     * Apply the token converter (and enhancer) for token store.
     *
     * @return the JwtTokenStore managing the tokens.
     */
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * This bean generates an token enhancer, which manages the exchange between JWT acces tokens and Authentication
     * in both directions.
     *
     * @return an access token converter configured with the authorization server's public/private keys
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
            new ClassPathResource(uaaProperties.getKeyStore().getName()), uaaProperties.getKeyStore().getPassword().toCharArray())
            .getKeyPair(uaaProperties.getKeyStore().getAlias());
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
}
