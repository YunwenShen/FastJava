package com.cucci.common.vo;

import com.cucci.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回前端结果
 *
 * @author shenyw
 **/
@Data
public class Result implements Serializable {

    /**
     * 状态码
     */
    public int code;

    /**
     * 返回提示消息
     */
    public String message;

    /**
     * 返回数据
     */
    public Object data;

    /**
     * 操作日志
     */
    public OperateLog log;

    private Result(ResultCode resultCode, String message, Object data, OperateLog log) {
        this.code = resultCode.getCode();
        this.message = message;
        this.data = data;
        this.log = log;
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
        return new Result(ResultCode.SUCCESS, message, data, OperateLog.NOT_LOGGED);
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
        return new Result(ResultCode.SUCCESS, message, null, OperateLog.NOT_LOGGED);
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
