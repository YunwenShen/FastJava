package com.cucci.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 简单job
 *
 * @author shenyw@citycloud.com.cn
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
     * 是否重复
     */
    private boolean repeat;

}
