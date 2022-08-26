package com.mybatisplus;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class GeneratorTest {
    //代码生成器测试
    @Test
    public void testGenerator() {
        GlobalConfig config = new GlobalConfig();
        //1、全局配置
        config.setActiveRecord(true)
                .setAuthor("jpzhou")
                .setOutputDir("C:\\MyDeveloperTools\\CodeTools\\IdeaProjects\\Study\\MyBatisPlusStudy01\\src\\main\\java")
                .setFileOverride(false)
                .setIdType(IdType.AUTO)
                .setServiceName("%sService")
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        //2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false")
                .setUsername("root")
                .setPassword("123456");
        //3、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("tbl_")
                .setInclude("tbl_employee");

        //4、包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.mybatisplus")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("beans")
                .setXml("mapper");

        //5、整合配置
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(pkConfig);

        generator.execute();
    }
}
