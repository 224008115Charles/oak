package cn.zhangxd.oak.uaa.generator;

import cn.zhangxd.oak.core.generator.BaseGenerator;
import cn.zhangxd.oak.core.generator.SimpleGenerator;
import org.junit.Test;

/**
 * 代码生成
 *
 * @author zhangxd
 */
public class CodeGenerator {

    private static final String[] BASE_INCLUDE_TABLES = new String[]{
        "user", "role"
    };
    private static final String[] SIMPLE_INCLUDE_TABLES = new String[]{
        "user_role"
    };
    private static final String BASE_PACKAGE = "cn.zhangxd.oak.uaa";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oak_uaa";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static final String AUTHOR = "zhangxd";

    @Test
    public void generateBaseCode() {
        new BaseGenerator()
            .globalConfig(AUTHOR)
            .dataSourceConfig(DB_URL, DB_USERNAME, DB_PASSWORD)
            .packageConfig(BASE_PACKAGE)
            .injectionConfig()
            .templateConfig()
            .strategyConfig(BASE_INCLUDE_TABLES)
            .execute();
    }

    @Test
    public void generateSimpleCode() {
        new SimpleGenerator()
            .globalConfig(AUTHOR)
            .dataSourceConfig(DB_URL, DB_USERNAME, DB_PASSWORD)
            .packageConfig(BASE_PACKAGE)
            .injectionConfig()
            .templateConfig()
            .strategyConfig(SIMPLE_INCLUDE_TABLES)
            .execute();
    }


}
