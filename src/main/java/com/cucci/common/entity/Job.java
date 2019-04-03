package com.cucci.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 普通任务 Cron
 *
 * @author shenyw
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class Job extends BaseJob {

    /**
     * cron 表达式
     */
    private String cron;

    /**
     * 执行参数
     */
    private Map<String, Object> param;
}
