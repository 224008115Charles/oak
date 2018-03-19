package cn.zhangxd.oak.service.system.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxd
 */
@Configuration
@EnableFeignClients(basePackages = "cn.zhangxd.oak.service.system")
public class FeignConfiguration {

}
