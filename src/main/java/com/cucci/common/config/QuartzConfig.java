package com.cucci.common.config;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * 定时任务配置
 *
 * @author shenyw@citycloud.com.cn
 **/
@Configuration
public class QuartzConfig {

    @Bean
    public JobFactory initJobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * 配置properties属性
     *
     * @return
     */
    @Bean("quartzProperties")
    public Properties quartzProperties(QuartzProperties properties) {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        Map<String, String> map = properties.getProperties();
        Properties p = new Properties();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            p.put(entry.getKey(), entry.getValue());
        }
        return p;
    }

    @ConfigurationProperties(prefix = "spring.quartz.properties")
    @Bean
    public SchedulerFactoryBean quartzScheduler(@Qualifier("primary") DataSource dataSource, JobFactory jobFactory, @Qualifier("quartzProperties") Properties properties) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //可选,QuartzScheduler启动时更新己存在的Job,这样就不用每次修改targetObject后删除qrtz_job_details表对应记录
        factory.setOverwriteExistingJobs(true);
        // 设置自行启动
        factory.setAutoStartup(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(properties);
        return factory;
    }

    /**
     * 配置JobFactory,为quartz作业添加自动连接支持
     */
    public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            beanFactory.autowireBean(job);
            return job;
        }
    }
}
