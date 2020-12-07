package com.meettingfilm.backend.pojo_generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/*
    数据层内容生成
 */
public class EntityGeneratorInsert {

    // 生成输出目录，定位到工程的java目录下
    //D:\chuck\ideaProjects\SpringCloud\backend_parent\backend_common\src\main\java\com
    private static String outputDir = "D:\\server_projects\\SpringCloudDemo\\pojo_generator\\src\\main\\java";
    //D:\server_projects\SpringCloudDemo\pojo_generator\src\main\java
            // 生成类的作者
    private static String author = "chuck";
    // 数据源相关配置
/*    ip:120.78.221.149
    port:20000
    username:test
    password:Lzhlmcl@2017
    database:qingshiyuan*/
 /*   private static String url = "jdbc:mysql://192.168.0.108:3306/yingzhuang?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "root";
    private static String userPwd = "123456";
    */

    /*
    private static String url = "jdbc:mysql://120.78.221.149:20000/qingshiyuan?autoReconnect=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "test";
    private static String userPwd = "Lzhlmcl@2017";
        */


    private static String url = "jdbc:mysql://120.78.221.149:20000/retire?autoReconnect=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "retire";
    private static String userPwd = "04wGyfqGZxflDeZR";

    /*spring.datasource.url=jdbc:mysql://120.78.221.149:20000/retire?autoReconnect=true&amp;characterEncoding=UTF-8
    spring.datasource.username=retire
    spring.datasource.password=04wGyfqGZxflDeZR
    spring.datasource.driverClassName=com.mysql.jdbc.Driver*/

    // DAO的包路径
    private static String daoPackage = "com.ilinklink.dao";
    // 待生成的表名，注意是覆盖更新
    private static String[] tableNames;

    static{
        tableNames = new String[]{
               /* "yz_project",
                "yz_project_attachment",
                "yz_project_log",
                "yz_project_plan",
                "yz_clue",
                "yz_clue_attachment",
                "yz_tenant_user",
*/
                "pt_system_param",

        };
    }

    public static void main (String [] args){
        entityGenerator();
    }



    public static void entityGenerator() {

        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new BeetlTemplateEngine());


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 使用自定义模板，不想要生成就设置为null,如果不设置null会使用默认模板
        templateConfig.setEntity("templates/lombok_swagger2_entity_sql_insert.java");
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setController(null);
        // templateConfig.setMapper(null);
        //templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);







        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setSwagger2(true);
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
        //避免把tinyint变为boolean
        dsc.setTypeConvert(new MySqlTypeConvertCustom());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[]{"_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(tableNames);
        strategy.setEntityLombokModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity(daoPackage + ".entity");
        pc.setMapper(daoPackage + ".mapper");
        pc.setXml(daoPackage + ".mapper.xml");
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

    /**
     * 自定义类型转换
     */
   static class MySqlTypeConvertCustom extends MySqlTypeConvert implements ITypeConvert {
        @Override
        public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
            String t = fieldType.toLowerCase();
            if (t.contains("tinyint")) {
                return DbColumnType.INTEGER;
            }
            if (t.contains("int")) {
                return DbColumnType.INTEGER;
            }
            if (t.contains("datetime")) {
                return DbColumnType.DATE;
            }
            if (t.contains("timestamp")) {
                return DbColumnType.DATE;
            }
            return super.processTypeConvert(globalConfig, fieldType);
        }
    }

}
