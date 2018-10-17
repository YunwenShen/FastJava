package com.cucci.common.base;

import com.cucci.common.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * controller基类
 *
 * @author shenyw
 **/
public class BaseController {

    /**
     * 统一异常处理
     *
     * @param e 异常类型
     * @return result
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleException(RuntimeException e) {
        return Result.createError(e.getMessage());
    }

}
