package com.cucci.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Map;

/**
 * 简单任务
 *
 * @author shenyw
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleJob extends BaseJob {

    /**
     * 开始执行时间
     */
    private Date startDate;

    /**
     * 多久执行一次
     */
    private Integer interval;

    /**
     * 重复几次
     */
    private Integer repeat;

    /**
     * 参数
     */
    private Map<String, Object> param;
}
