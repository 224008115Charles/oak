package cn.zhangxd.oak.service.system.config;

import cn.zhangxd.oak.core.config.OakProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Service.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link OakProperties} for a good example.
 *
 * @author zhangxd
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

}
