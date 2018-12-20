package com.cucci.common.base;

import lombok.Data;

/**
 * 表单基类
 *
 * @author shenyw
 **/
@Data
public class BaseQueryForm implements QueryForm {

    private static final long serialVersionUID = -8753593462701650513L;
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
