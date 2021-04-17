package com.gouwo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @Author: asky
 * @Date: 2021/3/5 18:21
 * @Desc: 代码自动生成工具
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String input = scanner.next();
            if (StringUtils.isNotEmpty(input)) {
                return input;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {

        //用来获取Mybatis-Plus.properties文件的配置信息
        final ResourceBundle rb = ResourceBundle.getBundle("mybatis-plus");

        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        String modulePath = scanner("子项目名!");
        //生成路径(一般都是生成在此项目的src/main/java下面)
        gc.setOutputDir(projectPath + "\\" + modulePath + "\\src\\main\\java");
        //第二次生成会把第一次的覆盖
        gc.setFileOverride(true);
        //不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        //XML二级缓存
        gc.setEnableCache(false);
        //XML ResultMap
        gc.setBaseResultMap(true);
        //XML columnList  在xml中生产基础列
        gc.setBaseColumnList(true);
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        //作者
        gc.setAuthor(rb.getString("author"));

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(rb.getString("url"));
        dsc.setDriverName(rb.getString("driverName"));
        //数据库类型
        dsc.setDbType(DbType.MYSQL);
        dsc.setUsername(rb.getString("userName"));
        dsc.setPassword(rb.getString("password"));
        mpg.setDataSource(dsc);

        //自定义文件命名，注意%s 会自动填充表实体属性
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setEntityName("%sModel");
        mpg.setGlobalConfig(gc);

        //包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("包名"));
        pc.setParent(rb.getString("parent"));
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service" + ".impl");
        pc.setMapper("mapper");
        pc.setEntity("model");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建,这里调用默认的方法
                checkDir(filePath);
                //对于已存在的文件，只需重复生成 model 和 mapper.xml
                File file = new File(filePath);
                boolean exist = file.exists();
                if (exist) {
                    return filePath.endsWith("Mapper.xml") || FileType.ENTITY == fileType;
                }
                //不存在的文件都需要创建
                return true;
            }
        });

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名，如果Entity设置了前后缀、此处注意xml的名称会跟着发生变化！
                return projectPath + "\\" + modulePath + "\\src\\main\\resources\\mapper\\" + tableInfo.getEntityName().
                        substring(0, tableInfo.getEntityName().length() - 5) + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        //表名生成策略,下划线到驼峰的命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //开启全局大写命名
        strategy.setCapitalMode(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //需要生成的表,如果为空，则默认读取数据库的所有表名
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //此处可以修改表前缀
        strategy.setTablePrefix(rb.getString("tablePrefix").split(","));
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        mpg.setStrategy(strategy);

        // set freemarker engine
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        //执行生成
        mpg.execute();
    }

}