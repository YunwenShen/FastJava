package com.cucci.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 表单基类
 *
 * @author shenyw
 **/
@Data
public class BaseForm implements Serializable {

    /**
     * 页码
     **/
    private Integer pageNo = 1;

    /**
     * 每页条数
     **/
    private Integer pageSize = 25;

    /**
     * 排序名
     */
    private String sortName;

    /**
     * 是否倒序
     */
    private Boolean isDesc;

    /**
     * 表的别名
     */
    private String prefix;
}
