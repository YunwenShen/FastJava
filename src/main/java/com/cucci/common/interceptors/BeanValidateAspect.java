package com.cucci.common.interceptors;

import com.cucci.common.base.SaveForm;
import com.cucci.common.vo.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
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

    @Pointcut("execution(com.cucci.common.vo.Result com.cucci.common.controller.*.*(..)) && args(.., com.cucci.common.base.SaveForm)")
    public void joinPoint() {
    }

    @Around("joinPoint()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        // 获得该切入点中参数实现了SaveForm的对象
        List<Object> list = filterSaveFormArgs(jp);
        // 验证结果
        String validMsg = validate(list);
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
    private List<Object> filterSaveFormArgs(ProceedingJoinPoint jp) {
        Object[] objects = jp.getArgs();
        List<Object> list = new ArrayList<>();
        for (Object object : objects) {
            if (object instanceof SaveForm) {
                list.add(object);
            }
        }
        return list;
    }

    /**
     * 验证表单内容
     * <p>
     * TODO 目前暂无法支持分组校验
     *
     * @param list 实现了SaveForm接口的对象
     * @return 验证结果
     */
    private String validate(List<Object> list) {
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            Set<ConstraintViolation<Object>> validators = validator.validate(o);
            for (ConstraintViolation<Object> constraintViolation : validators) {
                sb.append(constraintViolation.getMessage()).append(";");
            }
        }
        return sb.toString();
    }
}
