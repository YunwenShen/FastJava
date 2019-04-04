package com.cucci.common.interceptors;

import com.cucci.common.annotions.Group;
import com.cucci.common.vo.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 表单参数验证
 *
 * @author shenyw
 **/
@Aspect
@Component
public class BeanValidateAspect {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Pointcut("execution(com.cucci.common.vo.Result com.cucci.common.controller.*.*(..)) && args(com.cucci.common.base.SaveForm, ..)")
    public void joinPoint() {
    }

    @Around("joinPoint()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        // 获得该切入点中参数实现了SaveForm的对象
        ValidEntity entity = filterSaveFormArgs(jp);
        // 验证结果
        String validMsg = validate(entity);
        if (StringUtils.isEmpty(validMsg)) {
            return jp.proceed();
        } else {
            return Result.createError(validMsg);
        }
    }

    /**
     * 过滤出实现了SaveForm接口的参数
     *
     * @param jp 切入点
     * @return 实现了SaveForm接口的对象集合
     */
    private ValidEntity filterSaveFormArgs(ProceedingJoinPoint jp) {
        // 切点的方法的签名
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        // 表单实体
        Object form = jp.getArgs()[0];
        // 方法上的ValidGroup注解
        Group methodValid = method.getAnnotation(Group.class);
        // 参数上的ValidGroup注解
        Annotation[] annotations = method.getParameterAnnotations()[0];
        Group paramValid = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Group) {
                paramValid = (Group) annotation;
            }
        }
        Group group = (paramValid == null) ? methodValid : paramValid;
        return new ValidEntity(form, group);
    }

    /**
     * 验证表单内容
     *
     * @param entity
     * @return 验证结果
     */
    private String validate(ValidEntity entity) {
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<Object>> validators;
        if (entity.group == null || entity.group.value().length == 0) {
            validators = validator.validate(entity.form);
        } else {
            validators = validator.validate(entity.form, entity.group.value());
        }
        for (ConstraintViolation<Object> constraintViolation : validators) {
            sb.append(constraintViolation.getMessage()).append(";");
        }
        return sb.toString();
    }

    private class ValidEntity {
        /**
         * 验证的表单
         */
        private Object form;
        /**
         * 需要校验的组
         */
        private Group group;

        ValidEntity(Object form, Group group) {
            this.form = form;
            this.group = group;
        }
    }
}
