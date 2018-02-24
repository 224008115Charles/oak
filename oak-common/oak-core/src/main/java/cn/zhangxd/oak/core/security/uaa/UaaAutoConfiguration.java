package cn.zhangxd.oak.core.security.uaa;

import cn.zhangxd.oak.core.config.OakProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author zhangxd
 */
@Configuration
@ConditionalOnClass({ClientCredentialsResourceDetails.class, LoadBalancerClient.class})
@ConditionalOnProperty("oak.security.client-authorization.client-id")
public class UaaAutoConfiguration {

    private OakProperties oakProperties;

    public UaaAutoConfiguration(OakProperties oakProperties) {
        this.oakProperties = oakProperties;
    }

    @Bean
    public LoadBalancedResourceDetails loadBalancedResourceDetails(LoadBalancerClient loadBalancerClient) {
        LoadBalancedResourceDetails loadBalancedResourceDetails = new LoadBalancedResourceDetails(loadBalancerClient);
        OakProperties.Security.ClientAuthorization clientAuthorization = oakProperties.getSecurity()
            .getClientAuthorization();
        loadBalancedResourceDetails.setAccessTokenUri(clientAuthorization.getAccessTokenUri());
        loadBalancedResourceDetails.setTokenServiceId(clientAuthorization.getTokenServiceId());
        loadBalancedResourceDetails.setClientId(clientAuthorization.getClientId());
        loadBalancedResourceDetails.setClientSecret(clientAuthorization.getClientSecret());
        return loadBalancedResourceDetails;
    }
}
