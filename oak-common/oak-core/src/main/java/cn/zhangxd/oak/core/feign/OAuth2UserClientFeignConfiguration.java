package cn.zhangxd.oak.core.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangxd
 */
public class OAuth2UserClientFeignConfiguration {

    @Bean(name = "userFeignClientInterceptor")
    public RequestInterceptor getUserFeignClientInterceptor() {
        return new UserFeignClientInterceptor();
    }
}
