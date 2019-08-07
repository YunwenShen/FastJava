package com.cucci.common.listeners;


import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

import java.io.Serializable;

/**
 * 请假流程开始
 *
 * @author shenyw@citycloud.com.cn
 **/
@Slf4j
public class StartUpListener implements TaskListener, Serializable {

    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("【请假申请流程开始了】");
        log.info("输入的变量：" + expression.getValue(delegateTask));
    }
}
