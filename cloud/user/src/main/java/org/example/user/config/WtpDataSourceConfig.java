package org.example.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.masterslave.LoadBalanceStrategyConfiguration;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"org.example.user.pojo.mapper"},sqlSessionTemplateRef = "sqlSessionTemplate")
public class WtpDataSourceConfig {

    @Value("${spring.shardingsphere.datasource.props.sql-show}")
    private String sqlShow;

    @Bean
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.wtp-master")
    public DataSource wtpMasterDataSource() {
        DruidDataSource wtpMasterDataSource = new DruidDataSource();
        return wtpMasterDataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.wtp-slave")
    public DataSource wtpSlaveDataSource() {
        DruidDataSource wtpSlaveDataSource = new DruidDataSource();
        return wtpSlaveDataSource;
    }

    @Bean
    public DataSource wtpDataSource(@Qualifier("wtpMasterDataSource") DataSource wtpMasterDataSource,
                                    @Qualifier("wtpSlaveDataSource") DataSource wtpSlaveDataSource) throws SQLException {
        HashMap<String, DataSource> stringDataSourceHashMap = new HashMap<>();
        stringDataSourceHashMap.put("wtpSlaveDataSource", wtpSlaveDataSource);
        stringDataSourceHashMap.put("wtpMasterDataSource", wtpMasterDataSource);
        LoadBalanceStrategyConfiguration loadBalanceStrategyConfiguration = new LoadBalanceStrategyConfiguration("round_robin");
        MasterSlaveRuleConfiguration masterSlaveRuleConfiguration = new MasterSlaveRuleConfiguration("wtp-master-slave", "wtpMasterDataSource", Arrays.asList("wtpMasterDataSource"), loadBalanceStrategyConfiguration);
        Properties properties = new Properties();
        properties.setProperty("sql.show", sqlShow);
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(stringDataSourceHashMap, masterSlaveRuleConfiguration, properties);
        return dataSource;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("wtpDataSource") DataSource wtpDataSource, Interceptor mybatisPlusInterceptor) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(wtpDataSource);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Interceptor[] plugins = new Interceptor[]{mybatisPlusInterceptor};
        mybatisSqlSessionFactoryBean.setPlugins(plugins);
        try {
            mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/wtp/*.xml"));
            return mybatisSqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    @Bean
    public DataSourceTransactionManager wtpDataSourceTransactionManager(@Qualifier("wtpDataSource") DataSource wtpDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(wtpDataSource);
        return dataSourceTransactionManager;
    }
}
