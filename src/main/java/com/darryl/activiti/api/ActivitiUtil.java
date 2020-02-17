package com.darryl.activiti.api;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 工作流的api
 * @Date: created in 2020/2/17 10:42
 */

public class ActivitiUtil {

    private static Logger log = LoggerFactory.getLogger(ActivitiUtil.class);

    //流程引擎ProcessEngine对象 ,所有操作都要依赖流程引擎对象
    private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //由流程引擎创建各service
    private static RepositoryService repositoryService = processEngine.getRepositoryService();
    //创建运行时服务
    private static RuntimeService runtimeService = processEngine.getRuntimeService();
    //创建任务服务
    private static TaskService taskService = processEngine.getTaskService();
    //历史任务相关的服务
    private static HistoryService historyService = processEngine.getHistoryService();
    //身份服务
    //IdentityService identityService = processEngine.getIdentityService();
    //管理服务
    private static ManagementService managermentService = processEngine.getManagementService();

    /**
     * 部署一个工作流
     * @param name 部署的名称
     * @param path bpmn文件路径
     */
    public static void deploymenyProcessDefinition(String name, String path) {
        //与流程定义和部署对象相关的service//创建一个部署对象//添加部署的名称//从classpath资源中加载文件,一次加载一个(相对路径)//部署
        Deployment deployment = repositoryService.createDeployment()
                .name(name)
                .addClasspathResource(path)
                .deploy();
        log.debug("流程Id: {}", deployment.getId());
        log.debug("流程名称: {}", deployment.getName());
    }

    /**
     * 启动一个工作流流程
     * @param processDedinitionKey 流程定义的key
     */
    public static void startProcessInstance(String processDedinitionKey) {
        //与正在执行的流程实例和执行对象相关的service //通过key启动流程实例,key对应***.bpmn文件中id的属性值，启动一个流程startProcessInstanceByKey(*...)有很多重载的方法
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDedinitionKey);
        log.debug("流程实例id: {}", pi.getId());
        log.debug("流程定义id: {}", pi.getProcessDefinitionId());
    }

    /**
     * 查询指定用户的当前任务
     * @param assignee 指定用户
     * @return 指定用户的当前任务列表
     */
    public static List<Task> findMyProcessTask(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    /**
     * 完成工作流程中的一个任务
     * @param taskId 任务id，可以通过获取相关的任务信息获取到该id
     */
    public static void completeTask(String taskId) {
        taskService.complete(taskId);
        log.debug("任务id: {}, 已完成", taskId);
    }

    /**
     * 将组任务分配给个人任务，拾取任务
     * @param taskId 任务id
     * @param userId 用户id
     */
    public static void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
        log.debug("任务id: {}, 被分配给用户： {} 处理。",taskId, userId);
    }

}
