package com.cucci.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cron job
 *
 * @author shenyw@citycloud.com.cn
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CronJob extends BaseJob {

    /**
     * cron 表达式
     */
    private String cron;
}
