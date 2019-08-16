package com.cucci.common.controller;

import com.cucci.common.vo.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * activiti
 *
 * @author shenyw@citycloud.com.cn
 **/
@RestController
@RequestMapping("/activiti")
@AllArgsConstructor
@Slf4j
public class ActivitiRestController {

    private RuntimeService runtimeService;
    private TaskService taskService;


    @RequestMapping("/start")
    public Result start(@RequestBody Map<String, Object> map) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", 4);
        variables.put("vacationMotivation", "I'm really tired!");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
        log.info("【休假申请已启动】");
        return Result.createSuccess("");
    }

    @RequestMapping("/complete")
    public Result complete() {
        List<Task> tasks = taskService.createTaskQuery().list();
        for (Task task : tasks) {
            log.info("Task available: " + task.getName());
        }
        return Result.createSuccess("");
    }
}
