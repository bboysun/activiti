package com.darryl.activiti.controller;

import com.darryl.activiti.activit_api.ActivitiUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/findProcessTask")
    public void findProcessTask(@RequestParam(name = "assignee") String assignee){
        List<Task> taskList = ActivitiUtil.findProcessTask(assignee);
        // 处理任务
        for (Task task : taskList) {
            System.out.println("task id : " + task.getId());
            System.out.println("task assignee : " + task.getAssignee());
        }
    }

    @ApiOperation(value="test activiti某个任务转给其他人处理", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "代理人", paramType = "query", required = true, dataType = "String")
    })
    @GetMapping("/transferAssignee")
    public boolean transferAssignee(@RequestParam(name = "taskId") String taskId, @RequestParam(name = "userId") String userId){
        return ActivitiUtil.transferAssignee(taskId, userId);
    }

    @ApiOperation(value="test activiti流程节点跳转", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "curTaskId", value = "当前任务id", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "targetFlowNodeId", value = "目标任务节点", paramType = "query", required = true, dataType = "String")
    })
    @GetMapping("/jump2TargetFlowNode")
    public void jump2TargetFlowNode(@RequestParam(name = "curTaskId") String curTaskId, @RequestParam(name = "targetFlowNodeId") String targetFlowNodeId){
        ActivitiUtil.jump2TargetFlowNode(curTaskId, targetFlowNodeId);
    }
}
