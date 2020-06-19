package com.cucci.common.base;

import com.cucci.common.exceptions.ApplicationException;
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
    @ExceptionHandler(ApplicationException.class)
    public Result handleApplicationException(ApplicationException e) {
        return Result.createError(e.getMessage());
    }


    /**
     * 统一异常处理
     *
     * @param e 异常类型
     * @return result
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.createError(e.getMessage());
    }
}
