package cn.zhangxd.oak.uaa.config;

import cn.zhangxd.oak.core.config.OakConstants;
import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhangxd
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "cn.zhangxd.oak.uaa.mapper")
public class MybatisConfiguration {

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    @Profile(OakConstants.SPRING_PROFILE_DEVELOPMENT)
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
