package com.meettingfilm.backend_common;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/*
    数据层内容生成
 */
@SpringBootTest
public class EntityGenerator {

    // 生成输出目录，定位到工程的java目录下
    //D:\chuck\ideaProjects\SpringCloud\backend_parent\backend_common\src\main\java\com
    private static String outputDir = "D:\\chuck\\ideaProjects\\SpringCloud\\backend_parent\\backend_common\\src\\main\\java";
    // 生成类的作者
    private static String author = "chuck";
    // 数据源相关配置
    private static String url = "jdbc:mysql://39.107.40.76:3306/meettingfilm?autoReconnect=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "admin";
    private static String userPwd = "123456";
    // DAO的包路径
    private static String daoPackage = "com.meettingfilm.backend_common.dao";
    // 待生成的表名，注意是覆盖更新
    private static String[] tableNames;

    static{
        tableNames = new String[]{
                "mooc_backend_user_t",
                "mooc_user_t",
                "mooc_banner_t",
                "mooc_cat_dict_t",
                "mooc_source_dict_t",
                "mooc_year_dict_t",
                "mooc_film_t",
                "mooc_film_info_t",
                "mooc_actor_t",
                "mooc_film_actor_t",
                "mooc_brand_dict_t",
                "mooc_area_dict_t",
                "mooc_hall_dict_t",
                "mooc_cinema_t",
                "mooc_field_t",
                "mooc_hall_film_info_t",
                "mooc_order_t",
                "mooc_order_2017_t",
                "mooc_order_2018_t"





        };
    }


     @Test
     public void entityGenerator() {
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new BeetlTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setAuthor(author);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(userPwd);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[]{"_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(tableNames);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity(daoPackage+".entity");
        pc.setMapper(daoPackage+".mapper");
        pc.setXml(daoPackage+".mapper.xml");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        mpg.setCfg(cfg);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));

    }

}
