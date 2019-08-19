package com.cucci.common.interceptors;

import com.cucci.common.annotions.Logger;
import com.cucci.common.form.UserSaveForm;
import com.cucci.common.vo.OperateLog;
import com.cucci.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志切面
 *
 * @author shenyw
 **/
@Aspect
@Component
@Slf4j
public class LoggerAspect {

    /**
     * 方法的入参，多个入参使用P[i]获取
     */
    private static final String PARAMS = "P";
    /**
     * 方法的返回结果
     */
    private static final String RETURN = "R";
    /**
     * 异常信息
     */
    private static final String EXCEPTION = "E";

    @Pointcut("@annotation(com.cucci.common.annotions.Logger)")
    public void loggerPointcut() {
    }

    @Around("loggerPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object rtv;
        Map<String, Object> vars = new HashMap<>();
        vars.put(PARAMS, joinPoint.getArgs());
        try {
            rtv = joinPoint.proceed();
            vars.put(RETURN, rtv);
            parse(joinPoint, vars, true);
        } catch (Throwable throwable) {
            vars.put(EXCEPTION, throwable);
            parse(joinPoint, vars, false);
            throw new RuntimeException(throwable);
        }
        return rtv;
    }

    /**
     * 解析日志内容
     *
     * @param joinPoint
     * @param vars
     * @param flag
     */
    private void parse(ProceedingJoinPoint joinPoint, Map<String, Object> vars, boolean flag) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Logger logger = method.getAnnotation(Logger.class);
            String desc = logger.desc();
            if (logger.spel()) {
                // 通过SpringEL的方式来拼装操作日志
                ExpressionParser parser = new SpelExpressionParser();
                StandardEvaluationContext context = new StandardEvaluationContext();
                context.setVariables(vars);
                Expression expression = parser.parseExpression(logger.desc());
                desc = expression.getValue(context, String.class);
            }
            // 封装成操作日志
            OperateLog operateLog = wrapOperateLog(logger, desc, flag);
            // TODO 操作日志或者通过消息队列的方式
        } catch (Exception ignored) {
        }
    }

    /**
     * 封装成 operateLog
     *
     * @param logger
     * @param desc
     * @param flag
     * @return
     */
    private OperateLog wrapOperateLog(Logger logger, String desc, boolean flag) {
        OperateLog operateLog = new OperateLog();
        operateLog.setModuleType(logger.module());
        operateLog.setOperateType(logger.operate());
        operateLog.setOperateTime(new Date());
        operateLog.setContent(desc);
        operateLog.setSuccess(flag);
        // TODO 获取当前登录人的信息
        operateLog.setId(1);
        return operateLog;
    }

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 模拟方法的入参、返回值、异常信息
        UserSaveForm saveForm = new UserSaveForm();
        saveForm.setAge(22);
        saveForm.setAddr("浙江杭州");
        saveForm.setName("cucci");
        Map<String, Object> vars = new HashMap<>();
        vars.put(PARAMS, new Object[]{saveForm});
        vars.put(RETURN, Result.createSuccess("操作失败"));
        vars.put(EXCEPTION, new RuntimeException("系统错误"));
        context.setVariables(vars);
        // 解析Spring EL
        Expression expression = parser.parseExpression("(#E==null) ?" +
                " ('注册新用户成功, 用户名称：' + #P[0].name + ',用户年龄：' + #P[0].age + ', 用户住址：' + #P[0].addr ) :" +
                "('注册新用户失败, 失败原因：' + #E.message)");
        String parseResult = expression.getValue(context, String.class);
        System.out.println(parseResult);

    }
}
