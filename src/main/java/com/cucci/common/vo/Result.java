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

    private Result(ResultCode resultCode, String message, Object data) {
        this.code = resultCode.getCode();
        this.message = message;
        this.data = data;
    }

    /**
     * 构建失败的返回结果
     *
     * @param message 信息
     * @return result
     */
    public static Result createError(String message) {
        return new Result(ResultCode.ERROR, message, null);
    }


    /**
     * 构建成功的返回结果
     *
     * @param message 信息
     * @param data    返回数据
     * @return result
     */
    public static Result createSuccess(String message, Object data) {
        return new Result(ResultCode.SUCCESS, message, data);
    }

    /**
     * 构建成功的返回结果
     *
     * @param message 信息
     * @return result
     */
    public static Result createSuccess(String message) {
        return new Result(ResultCode.SUCCESS, message, null);
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
