package cn.zhangxd.oak.account.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangxd
 */
@Configuration
@EnableFeignClients(basePackages = "cn.zhangxd.oak.service.account")
public class FeignConfiguration {

}
