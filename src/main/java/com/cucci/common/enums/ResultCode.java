package com.cucci.common.enums;


/**
 * 返回状态码
 *
 * @author shenyw
 **/
public enum ResultCode {

    SUCCESS(200),
    ERROR(500),
    NO_AUTH(400),
    NO_LOGIN(401);

    private int code;

    public int getCode() {
        return code;
    }

    ResultCode(int code) {
        this.code = code;
    }
}
