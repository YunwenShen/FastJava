package com.cucci.common.entity;

import lombok.Data;
import org.quartz.Job;
import org.quartz.JobDataMap;

/**
 * quartz实体类
 *
 * @author shenyw@citycloud.com.cn
 **/
@Data
public class BaseJob {

    /**
     * 任务 名称
     */
    private String jobName;

    /**
     * 任务 所属组名称
     */
    private String jobGroupName;

    /**
     * 触发器 名称
     */
    private String triggerName;

    /**
     * 任务 所属组名称
     */
    private String triggerGroupName;

    /**
     * 任务 关联的类
     */
    private Class<? extends Job> clazz;

    /**
     * 参数
     */
    private JobDataMap param;

}
