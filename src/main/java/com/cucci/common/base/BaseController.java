package com.cucci.common.base;

import com.cucci.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * controller基类
 *
 * @author shenyw
 **/
@ControllerAdvice
public class BaseController {

    /**
     * 统一异常处理
     *
     * @param e 异常类型
     * @return result
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        // TODO 可以进行保存异常日志
        return Result.createError(e.getMessage());
    }

}
