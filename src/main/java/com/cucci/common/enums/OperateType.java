package com.cucci.common.enums;

/**
 * 日志操作类型
 *
 * @author shenyw
 **/
public enum OperateType {
    CREATE(1, "新增"),
    UPDATE(2, "更新"),
    DELETE(3, "删除");

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    OperateType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
