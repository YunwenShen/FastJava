package com.cucci.common.enums;

/**
 * 操作模块
 *
 * @author shenyw@citycloud.com.cn
 **/
public enum ModuleType {

    OA(1, "用户系统"),
    SYS(2, "系统配置");

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    ModuleType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
