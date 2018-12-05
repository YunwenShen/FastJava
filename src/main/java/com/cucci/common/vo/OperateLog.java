package com.cucci.common.vo;

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

    /**
     * 不记录操作日志
     */
    public static final OperateLog NOT_LOGGED = notLogged();

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
     * 操作对象
     */
    private String relatedName;

    /**
     * 操作对象关联id
     */
    private Integer relatedId;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 是否记录日志
     */
    private boolean record = true;


    /**
     * 构造器
     *
     * @param id          操作人id
     * @param operateTime 操作时间
     * @param operateType 操作类型
     * @param relatedName 操作对象
     * @param relatedId   关联id
     * @param content     操作内容
     */
    public OperateLog(Integer id, Date operateTime, OperateType operateType, String relatedName, Integer relatedId, String content) {
        this.id = id;
        this.operateTime = operateTime;
        this.operateType = operateType;
        this.relatedName = relatedName;
        this.relatedId = relatedId;
        this.content = content;
    }

    /**
     * 创建新增日志
     *
     * @param id          操作人id
     * @param relatedName 关联对象名称
     * @param relatedId   关联对象id
     * @return log
     */
    public static OperateLog genCreateLog(Integer id, String relatedName, Integer relatedId) {
        return new OperateLog(id, new Date(), OperateType.CREATE, relatedName, relatedId, null);
    }

    /**
     * 创建修改日志
     *
     * @param id          操作人id
     * @param relatedName 关联对象
     * @param relatedId   关联对象id
     * @param content     修改内容
     * @return logs
     */
    public static OperateLog genUpdateLog(Integer id, String relatedName, Integer relatedId, String content) {
        return new OperateLog(id, new Date(), OperateType.UPDATE, relatedName, relatedId, content);
    }

    /**
     * 创建删除日志
     *
     * @param id          操作人id
     * @param relatedName 关联对象名称
     * @param relatedId   关联对象id
     * @return log
     */
    public static OperateLog genDeleteLog(Integer id, String relatedName, Integer relatedId) {
        return new OperateLog(id, new Date(), OperateType.DELETE, relatedName, relatedId, null);
    }

    private static OperateLog notLogged() {
        OperateLog notLogged = new OperateLog(null, null, null, null, null, null);
        notLogged.setRecord(false);
        return notLogged;
    }
}
