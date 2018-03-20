package cn.zhangxd.oak.uaa.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxd
 */
@Configuration
@EnableFeignClients(basePackages = "cn.zhangxd.oak.uaa")
public class FeignConfiguration {

}
