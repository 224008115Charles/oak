package cn.zhangxd.oak.gateway.internal.config;

import cn.zhangxd.oak.core.config.OakConstants;
import cn.zhangxd.oak.gateway.internal.aop.logging.LoggingAspect;


import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * @author zhangxd
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(OakConstants.SPRING_PROFILE_DEVELOPMENT)
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }
}
