package cn.zhangxd.oak.generator;

import org.junit.Test;

/**
 * 代码生成
 *
 * @author zhangxd
 */
public class CodeGenerator {

    private static final String[] BASE_INCLUDE_TABLES = new String[]{
        "sys_user", "sys_role", "sys_menu", "sys_org"
    };
    private static final String[] SIMPLE_INCLUDE_TABLES = new String[]{
        "sys_user_role", "sys_role_menu", "sys_user_org"
    };
    private static final String BASE_PACKAGE = "cn.zhangxd.oak.service.system";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oak_sys";
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
