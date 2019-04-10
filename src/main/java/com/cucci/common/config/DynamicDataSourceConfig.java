package com.cucci.common.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 */
@Configuration
public class DynamicDataSourceConfig {

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis-plus.typeAliasesPackage}")
    private String typeAliasesPackage;

    /**
     * 默认数据源
     *
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Bean
    public DataSource primary() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 第二个数据源
     *
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    @Bean
    public DataSource secondary() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源
     *
     * @param primary
     * @param secondary
     * @return
     */
    @Bean
    public DynamicDataSource initDynamicDataSource(@Qualifier("primary") DataSource primary, @Qualifier("secondary") DataSource secondary) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(primary);
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("primary", primary);
        targetDataSource.put("secondary", secondary);
        dynamicDataSource.setTargetDataSources(targetDataSource);
        return dynamicDataSource;
    }


    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(typeAliasesPackage);// 指定基包
//        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));//
        return fb.getObject();
    }

    /**
     * 配置事务
     *
     * @param dynamicDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    /**
     * 配置jdbcTemplate
     *
     * @param dynamicDataSource
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DynamicDataSource dynamicDataSource) {
        return new JdbcTemplate(dynamicDataSource);
    }

}
