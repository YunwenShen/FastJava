package com.cucci.common.vo;

import com.alibaba.druid.support.json.JSONUtils;
import com.cucci.common.enums.ResultCode;

import java.util.HashMap;

/**
 * 返回前端结果
 *
 * @author shenyw
 **/
public class Result<T> extends HashMap<String, Object> {

    private static final long serialVersionUID = 8936291267036437712L;

    private Result(ResultCode resultCode, String message, T data) {
        put("code", resultCode.getCode());
        put("msg", message);
        if (data != null) {
            put("data", data);
        }
    }

    /**
     * 构建失败的返回结果
     *
     * @param message 信息
     * @return result
     */
    public static <T> Result<T> createError(String message) {
        return new Result<>(ResultCode.ERROR, message, null);
    }

    /**
     * 构建成功的返回结果
     *
     * @param message 西悉尼
     * @param data    返回数据
     * @return result
     */
    public static <T> Result<T> createSuccess(String message, T data) {
        return new Result<>(ResultCode.SUCCESS, message, data);
    }

    /**
     * 构建成功的返回结果
     *
     * @param message 信息
     * @return result
     */
    public static <T> Result<T> createSuccess(String message) {
        return new Result<>(ResultCode.SUCCESS, message, null);
    }

    @Override
    public String toString() {
        return JSONUtils.toJSONString(this);
    }
}
