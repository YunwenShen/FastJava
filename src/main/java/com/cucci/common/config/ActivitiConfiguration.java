package com.cucci.common.config;

import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Objects;

/**
 * 工作流配置
 *
 * @author shenyw
 */
@Configuration
public class ActivitiConfiguration {

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(@Qualifier("activiti") DataSource dataSource, DataSourceTransactionManager transactionManager) throws IOException {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setAsyncExecutorActivate(false);
        configuration.setDeploymentResources(new PathMatchingResourcePatternResolver().getResources("classpath*:/bpmn/*.bpmn"));
        return configuration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration configuration) {
        ProcessEngineFactoryBean bean = new ProcessEngineFactoryBean();
        bean.setProcessEngineConfiguration(configuration);
        return bean;
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngineFactoryBean bean) throws Exception {
        return Objects.requireNonNull(bean.getObject()).getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngineFactoryBean bean) throws Exception {
        return Objects.requireNonNull(bean.getObject()).getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngineFactoryBean bean) throws Exception {
        return Objects.requireNonNull(bean.getObject()).getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngineFactoryBean bean) throws Exception {
        return Objects.requireNonNull(bean.getObject()).getHistoryService();
    }

    @Bean
    public ManagementService managerService(ProcessEngineFactoryBean bean) throws Exception {
        return Objects.requireNonNull(bean.getObject()).getManagementService();
    }

}