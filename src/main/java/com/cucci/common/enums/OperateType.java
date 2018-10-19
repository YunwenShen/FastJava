package com.cucci.common.enums;

/**
 * 日志操作类型
 *
 * @author shenyw@citycloud.com.cn
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

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperateType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
