package com.cucci.common.config;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
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
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(@Qualifier("activiti") DataSource dataSource, DataSourceTransactionManager transactionManager) {
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        spec.setDataSource(dataSource);
        spec.setTransactionManager(transactionManager);
        spec.setDatabaseSchemaUpdate("true");
        Resource[] resources = null;
        // 启动自动部署流程
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath*:bpmn/*.bpmn");
        } catch (IOException e) {
            e.printStackTrace();
        }
        spec.setDeploymentResources(resources);
        return spec;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration configuration) {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(configuration);
        return processEngineFactoryBean;
    }

    @Bean
    public RepositoryService repositoryService(SpringProcessEngineConfiguration configuration) throws Exception {
        return Objects.requireNonNull(processEngine(configuration).getObject()).getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(SpringProcessEngineConfiguration configuration) throws Exception {
        return Objects.requireNonNull(processEngine(configuration).getObject()).getRuntimeService();
    }

    @Bean
    public TaskService taskService(SpringProcessEngineConfiguration configuration) throws Exception {
        return Objects.requireNonNull(processEngine(configuration).getObject()).getTaskService();
    }

    @Bean
    public HistoryService historyService(SpringProcessEngineConfiguration configuration) throws Exception {
        return Objects.requireNonNull(processEngine(configuration).getObject()).getHistoryService();
    }

}