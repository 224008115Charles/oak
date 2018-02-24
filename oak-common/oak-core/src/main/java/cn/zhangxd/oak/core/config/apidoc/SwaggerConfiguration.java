package cn.zhangxd.oak.core.config.apidoc;

import cn.zhangxd.oak.core.config.OakConstants;
import cn.zhangxd.oak.core.config.OakProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Springfox Swagger configuration.
 * <p>
 * Warning! When having a lot of REST endpoints, Springfox can become a performance issue. In that case, you can use a
 * specific Spring profile for this class, so that only front-end developers have access to the Swagger view.
 *
 * @author zhangxd
 */
@Configuration
@ConditionalOnClass({ApiInfo.class, BeanValidatorPluginsConfiguration.class})
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@Profile(OakConstants.SPRING_PROFILE_SWAGGER)
public class SwaggerConfiguration {

    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    private final OakProperties oakProperties;

    public SwaggerConfiguration(OakProperties oakProperties) {
        this.oakProperties = oakProperties;
    }

    /**
     * Springfox configuration for the API Swagger docs.
     *
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxApiDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
            oakProperties.getSwagger().getContactName(),
            oakProperties.getSwagger().getContactUrl(),
            oakProperties.getSwagger().getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
            oakProperties.getSwagger().getTitle(),
            oakProperties.getSwagger().getDescription(),
            oakProperties.getSwagger().getVersion(),
            oakProperties.getSwagger().getTermsOfServiceUrl(),
            contact,
            oakProperties.getSwagger().getLicense(),
            oakProperties.getSwagger().getLicenseUrl(),
            new ArrayList<>());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .host(oakProperties.getSwagger().getHost())
            .protocols(new HashSet<>(Arrays.asList(oakProperties.getSwagger().getProtocols())))
            .apiInfo(apiInfo)
            .forCodeGeneration(true)
            .directModelSubstitute(java.nio.ByteBuffer.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .select()
            .paths(regex(oakProperties.getSwagger().getDefaultIncludePattern()))
            .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }

    /**
     * Springfox configuration for the management endpoints (actuator) Swagger docs.
     *
     * @param appName               the application name
     * @param managementContextPath the path to access management endpoints
     * @param appVersion            the application version
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxManagementDocket(@Value("${spring.application.name}") String appName,
                                                   @Value("${management.context-path}") String managementContextPath,
                                                   @Value("${info.project.version}") String appVersion) {

        String host = oakProperties.getSwagger().getHost();
        String[] protocols = oakProperties.getSwagger().getProtocols();

        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(new ApiInfo(appName + " management API", "Management endpoints documentation",
                appVersion, "", ApiInfo.DEFAULT_CONTACT, "", "", new ArrayList<>()))
            .groupName("management")
            .host(host)
            .protocols(new HashSet<>(Arrays.asList(protocols)))
            .forCodeGeneration(true)
            .directModelSubstitute(java.nio.ByteBuffer.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .select()
            .paths(regex(managementContextPath + ".*"))
            .build();
    }

}
