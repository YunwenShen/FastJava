package com.cucci.common.interceptors;

import com.cucci.common.constants.RegExpConstant;
import com.cucci.common.utils.RegExpUtil;
import com.cucci.common.vo.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作日志切面
 *
 * @author shenyw
 **/
@Aspect
@Service
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("execution(com.cucci.common.vo.Result com.cucci.common.service.impl.*.*(..))")
    public void joinPoint() {
    }

    @Around("joinPoint()")
    public Result around(ProceedingJoinPoint jp) {
        Result result;
        try {
            result = (Result) jp.proceed();
            if (result == null) {
                throw new RuntimeException("返回值不能为空");
            }
            if (result.getLog() == null) {
                logger.warn("该操作没有输出操作日志");
            }
            // TODO 可以将操作日志存储到数据中
            return result;
        } catch (Throwable e) {
            String format = "Class name: %s. Method name：%s. Args is : %s. Error Message: %s";
            String className = RegExpUtil.parse(jp.getThis().getClass().getName(), RegExpConstant.CLASS_NAME);
            String methodName = jp.getSignature().getName();
            String args = Arrays.toString(jp.getArgs());
            String message = String.format(format, className, methodName, args, e.getMessage());
            logger.error(message);
            return Result.createError("系统异常");
        }
    }


}