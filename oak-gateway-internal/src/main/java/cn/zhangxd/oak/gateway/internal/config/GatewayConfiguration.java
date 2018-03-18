package cn.zhangxd.oak.gateway.internal.config;

import cn.zhangxd.oak.gateway.internal.filter.accesscontrol.AccessControlFilter;
import cn.zhangxd.oak.gateway.internal.filter.responserewriting.SwaggerBasePathRewritingFilter;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxd
 */
@Configuration
public class GatewayConfiguration {

    @Configuration
    public static class SwaggerBasePathRewritingConfiguration {

        @Bean
        public SwaggerBasePathRewritingFilter swaggerBasePathRewritingFilter() {
            return new SwaggerBasePathRewritingFilter();
        }
    }

    @Configuration
    public static class AccessControlFilterConfiguration {

        @Bean
        public AccessControlFilter accessControlFilter(RouteLocator routeLocator, ApplicationProperties applicationProperties) {
            return new AccessControlFilter(routeLocator, applicationProperties);
        }
    }

}
