package com.darryl.activiti.controller;

import com.darryl.activiti.api.ActivitiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.task.Task;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Darryl
 * @Description: demo
 * @Date: created in 2020/2/7 17:56
 */
@RestController
public class ActivitiController {

    @ApiOperation(value="test swagger", notes="")
    @ApiImplicitParam(name = "str", value = "test", paramType = "query", required = true, dataType = "String")
    @GetMapping("/go")
    public String go(@RequestParam(name = "str") String str) {
        return str;
    }

    @ApiOperation(value="test activiti获取任务", notes="")
    @ApiImplicitParam(name = "assignee", value = "代理人", paramType = "query", required = true, dataType = "String")
    @GetMapping("/fidMyProcessTask")
    public void findMyProcessTask(@RequestParam(name = "assignee") String assignee){
        List<Task> taskList = ActivitiUtil.findMyProcessTask(assignee);
        // 处理任务
        for (Task task : taskList) {
            System.out.println("task id : " + task.getId());
            System.out.println("task assignee : " + task.getAssignee());
        }
    }
}
