package com.cucci.common.vo;

import com.cucci.common.enums.ResultCode;
import lombok.Data;

import java.util.HashMap;

/**
 * 返回前端结果
 *
 * @author shenyw
 **/
@Data
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 8936291267036437712L;

    private Result(ResultCode resultCode, String message, Object data, OperateLog log) {
        put("code", resultCode.getCode());
        put("msg", message);
        if (data != null) {
            put("data", data);
        }
        if (log != null) {
            put("log", log);
        }
    }

    /**
     * 构建失败的返回结果
     *
     * @param message 信息
     * @return result
     */
    public static Result createError(String message) {
        return new Result(ResultCode.ERROR, message, null, null);
    }

    /**
     * 构建成功的返回结果
     *
     * @param message 信息
     * @param data    返回数据
     * @param log     操作日志
     * @return result
     */
    public static Result createSuccess(String message, Object data, OperateLog log) {
        return new Result(ResultCode.SUCCESS, message, data, log);
    }

    /**
     * 构建成功的返回结果
     *
     * @param message 西悉尼
     * @param data    返回数据
     * @return result
     */
    public static Result createSuccess(String message, Object data) {
        return new Result(ResultCode.SUCCESS, message, data, null);
    }

    /**
     * 构建成功的返回结果
     *
     * @param message 信息
     * @param log     操作日志
     * @return result
     */
    public static Result createSuccess(String message, OperateLog log) {
        return new Result(ResultCode.SUCCESS, message, null, log);
    }

    /**
     * 构建成功的返回结果
     *
     * @param message 信息
     * @return result
     */
    public static Result createSuccess(String message) {
        return new Result(ResultCode.SUCCESS, message, null, null);
    }

}
