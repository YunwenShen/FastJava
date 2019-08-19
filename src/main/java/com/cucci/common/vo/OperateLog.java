package com.cucci.common.vo;

import com.cucci.common.enums.ModuleType;
import com.cucci.common.enums.OperateType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @author shenyw
 **/
@Data
public class OperateLog implements Serializable {


    private static final long serialVersionUID = -6118124764469547010L;

    /**
     * 操作人
     */
    private Integer id;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作类型
     */
    private OperateType operateType;

    /**
     * 操作模块
     */
    private ModuleType moduleType;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 是否操作成功
     */
    private boolean success;
}
