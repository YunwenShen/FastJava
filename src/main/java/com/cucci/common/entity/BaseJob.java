package com.cucci.common.entity;

import lombok.Data;

/**
 * 定时任务Job基类
 *
 * @author shenyw
 **/
@Data
public abstract class BaseJob {

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
    private Class clazz;

}
