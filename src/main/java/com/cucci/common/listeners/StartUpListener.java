package com.cucci.common.listeners;


import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

/**
 * 请假流程开始
 *
 * @author shenyw@citycloud.com.cn
 **/
@Slf4j
public class StartUpListener implements ActivitiEventListener {


    @Override
    public void onEvent(ActivitiEvent activitiEvent) {

    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
