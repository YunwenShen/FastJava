package com.cucci.common.base;

import lombok.Data;

import java.util.Date;

/**
 * 实体基类
 *
 * @author shenyw
 **/
@Data
public abstract class BaseEntity {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 创建id
     */
    private Integer createId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改id
     */
    private Integer modifyId;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 是否删除
     */
    private Boolean del;
}
