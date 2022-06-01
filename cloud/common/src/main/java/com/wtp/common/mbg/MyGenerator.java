package com.wtp.common.mbg;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;

public class MyGenerator {
    private static final String USERNAME = "erp_root";
    private static final String PASSWORD = "Ihoment2018";
    private static final String URL = "jdbc:mysql://rm-wz9677w4v49viaib2ho.mysql.rds.aliyuncs.com:3306/ihoment?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";

    /**
     * 生成model Mapper
     *
     * @param applicationName 模块名(与maven模块名严格一致最佳)
     * @param mode            生成model的模式eg:flat conditional hierarchical @see org.mybatis.generator.config.ModelType
     * @param packageName     生成文件包名
     * @param pluginPath      插件引用地址
     */
    public void generate(String applicationName, String mode, String packageName, String pluginPath) {
        try {
            Context context = new Context(ModelType.getModelType(mode));
            context.setId(applicationName);
            context.setTargetRuntime("MyBatis3");

            //connection configuration
            JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
            jdbcConnectionConfiguration.setUserId(USERNAME);
            jdbcConnectionConfiguration.setPassword(PASSWORD);
            jdbcConnectionConfiguration.setDriverClass("com.mysql.cj.jdbc.Driver");
            jdbcConnectionConfiguration.setConnectionURL(URL);
            context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

            //model generate configuration
            JavaModelGeneratorConfiguration modelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
            modelGeneratorConfiguration.setTargetPackage(packageName + ".dao.model");
            modelGeneratorConfiguration.setTargetProject(applicationName + "/src/main/java");
            modelGeneratorConfiguration.addProperty("constructorBased", "true");
            modelGeneratorConfiguration.addProperty("trimStrings", "true");
            context.setJavaModelGeneratorConfiguration(modelGeneratorConfiguration);

            //java mapper configuration
            JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
            javaClientGeneratorConfiguration.setTargetPackage(packageName + ".dao.mapper");
            javaClientGeneratorConfiguration.setTargetProject(applicationName + "/src/main/java");
            javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
            context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

            //xmlMapper generate configuration
            SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
            sqlMapGeneratorConfiguration.setTargetProject(applicationName + "/src/main/resources");
            sqlMapGeneratorConfiguration.setTargetPackage("mapper.generator");
            context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);


            //plugin
            PluginConfiguration pluginConfiguration = new PluginConfiguration();
            pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
            context.addPluginConfiguration(pluginConfiguration);

            //tables
            TableConfiguration tableConfiguration = new TableConfiguration(context);
            tableConfiguration.setTableName("app_shipping_address");
            //不使用实际列名
            tableConfiguration.addProperty("useActualColumnNames", "false");
            //某些特殊列重写(如果有) 比如这个public是关键字 如果生成的javaModel与关键字冲突
            ColumnOverride columnOverride = new ColumnOverride("public");
            columnOverride.setJavaProperty("publicStatus");
            tableConfiguration.addColumnOverride(columnOverride);
            //指定表的modelType
            if (!StringUtils.isEmpty(mode)) {
                tableConfiguration.setConfiguredModelType(mode);
            }
            //生成的java Mapper 重命名
            DomainObjectRenamingRule domainObjectRenamingRule = new DomainObjectRenamingRule();
            domainObjectRenamingRule.setSearchString("^ih");
            domainObjectRenamingRule.setReplaceString("");
            tableConfiguration.setDomainObjectRenamingRule(domainObjectRenamingRule);
            context.addTableConfiguration(tableConfiguration);

            Configuration configuration = new Configuration();
            configuration.addContext(context);

            DefaultShellCallback defaultShellCallback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, new ArrayList<>());
            myBatisGenerator.generate(null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
