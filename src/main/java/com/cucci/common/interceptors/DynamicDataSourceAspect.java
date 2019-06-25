package com.cucci.common.interceptors;

import com.cucci.common.annotions.DataSource;
import com.cucci.common.config.DynamicContextHolder;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Log4j2
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.cucci.common.annotions.DataSource) || @within(com.cucci.common.annotions.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class targetClass = point.getTarget().getClass();
        Method method = signature.getMethod();

        DataSource targetDataSource = (DataSource) targetClass.getAnnotation(DataSource.class);
        DataSource methodDataSource = method.getAnnotation(DataSource.class);
        if (targetDataSource != null || methodDataSource != null) {
            String value;
            if (methodDataSource != null) {
                value = methodDataSource.value();
            } else {
                value = targetDataSource.value();
            }

            DynamicContextHolder.setDB(value);
            if (log.isDebugEnabled()) {
                log.debug("set datasource is {}", value);
            }
        }

        try {
            return point.proceed();
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("clean datasource");
            }
        }
    }

}
