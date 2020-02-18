package com.darryl.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivitiApplicationTests {

    /***
     * 整个Activiti的生命周期经过了如下的几个步骤：
     * 1.流程部署
     * 2.启动流程实例
     * 3.执行流程对象(一个流程实例包含多执行对象)
     * 4.完成整个流程
     */
    //流程引擎ProcessEngine对象 ,所有操作都要依赖流程引擎对象
    //ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
    //由流程引擎创建各service
    //RepositoryService repositoryService=processEngine.getRepositoryService();
    //创建运行时服务
    //RuntimeService runtimeService=processEngine.getRuntimeService();
    //创建任务服务
    //TaskService taskService=processEngine.getTaskService();
    //历史任务相关的服务
    //HistoryService historyService=processEngine.getHistoryService();
    //身份服务
    // IdentityService identityService=processEngine.getIdentityService();
    //管理服务
    //ManagementService managermentService=processEngine.getManagementService();
    public ActivitiApplicationTests() {
    }

    /**
     * 工作流全流程
     */
    @Test
    public void workflow() {
        // 1.部署
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //由流程引擎创建各service
        RepositoryService repositoryService = processEngine.getRepositoryService();

        System.out.println("classpath is : " + Thread.currentThread().getContextClassLoader().getResource(""));
        //与流程定义和部署对象相关的service//创建一个部署对象//添加部署的名称//从classpath资源中加载文件,一次加载一个(相对路径)//部署
        Deployment deployment = repositoryService.createDeployment()
                .name("workflow")
                .addClasspathResource("processes/workflow.bpmn")
                .deploy();
        System.out.println("deployment id: " + deployment.getId());

        // 2.获取流程定义，根据deployment id
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        System.out.println("processDefinition id: " + processDefinition.getId());

        // 3.启动流程，根据流程定义的id获取对应的流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        System.out.println("processInstance id: " + processInstance.getId());


        // 4.获取指定用户在启动流程的任务节点
        TaskService taskService=processEngine.getTaskService();
        String assignee = "张三";
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskAssignee(assignee).singleResult();
        System.out.println("task id: " + task.getId());


        // 5.完成该任务，根据任务id，当该任务在流程中是最后一个任务时，再被完成后不会出现在ACT_RU_TASK表中
        taskService.complete(task.getId());

        // 6.获取该流程历史相关Service，根据流程实例的id
        System.out.println("----------------------------------流程实例流转-----------------------");
        HistoryService historyService=processEngine.getHistoryService();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstance.getId())
                .finished().list();
        for (HistoricActivityInstance hai : list) {
            System.out.println("活动ID:" + hai.getId());
            System.out.println("流程实例ID:" + hai.getProcessInstanceId());
            System.out.println("活动名称：" + hai.getActivityName());
            System.out.println("办理人：" + hai.getAssignee());
            System.out.println("开始时间：" + hai.getStartTime());
            System.out.println("结束时间：" + hai.getEndTime());
            System.out.println("=================================");
        }
    }

    // 部署一个工作流
    @Test
    public void deploymenyProcessDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //由流程引擎创建各service
        RepositoryService repositoryService = processEngine.getRepositoryService();


        System.out.println("classpath is : " + Thread.currentThread().getContextClassLoader().getResource(""));
        //与流程定义和部署对象相关的service//创建一个部署对象//添加部署的名称//从classpath资源中加载文件,一次加载一个(相对路径)//部署
        Deployment deployment = repositoryService.createDeployment()
                .name("leave_process11")
                .addClasspathResource("processes/leave.bpmn")
                .deploy();
        System.out.println("流程Id:" + deployment.getId());
        System.out.println("流程名称:" + deployment.getName());
    }


    // 启动一个流程
    @Test
    public void startProcessInstance() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //流程定义的key
        String processDedinitionKey = "leave";
        //与正在执行的流程实例和执行对象相关的service //通过key启动流程实例,key对应***.bpmn文件中id的属性值，启动一个流程startProcessInstanceByKey(*...)有很多重载的方法
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDedinitionKey);
        System.out.println("流程实例id:" + pi.getId());
        System.out.println("流程定义id:" + pi.getProcessDefinitionId());
    }

    // 查询指定用户的当前任务
    @Test
    public void findMyProcessTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService=processEngine.getTaskService();

        String assignee = "张三";
        //与正在执行任务管理相关的service，//创建任务查询对象 //指定个人任务查询 指定办理人 //返回结果集
        List<Task> tasklist = taskService.createTaskQuery().taskAssignee(assignee).list();
        if (tasklist != null && tasklist.size() > 0) {
            for (Task task : tasklist) {
                System.out.println("任务Id:" + task.getId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("任务的创建时间:" + task.getCreateTime());
                System.out.println("任务的办理人:" + task.getAssignee());
                System.out.println("流程实例Id:" + task.getProcessInstanceId());
                System.out.println("流程执行对象Id:" + task.getExecutionId());
                System.out.println("流程定义Id:" + task.getProcessDefinitionId());
            }
        }
    }

    // 查看流程
    @Test
    public void findProcessDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //与流程定义和部署对象相关的service //创建一个流程定义的查询 //按照版本的升序排列 //返回的结果集
        List<ProcessDefinition> proDefList = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        if (proDefList != null && proDefList.size() > 0) {
            for (ProcessDefinition pd : proDefList) {
                System.out.println("流程定义Id:" + pd.getId());
                System.out.println("流程定义名称:" + pd.getName());
                System.out.println("流程定义的key:" + pd.getKey());
                System.out.println("流程定义的版本:" + pd.getVersion());
                System.out.println("资源名称bpmn文件:" + pd.getResourceName());
                System.out.println("资源名称png文件:" + pd.getDiagramResourceName());
                System.out.println("部署对象Id:" + pd.getDeploymentId());
            }
        }
    }


    /**
     * 查询经办人目前的task
     * id=0cc0cfe0-4cac-11ea-b0ef-b8763fc3091d
     * name=项目组长
     * assinee=张三 --> Darryl
     * create time=Tue Feb 11 16:54:01 CST 2020
     * executionId=0cb759fd-4cac-11ea-b0ef-b8763fc3091d
     *
     * id=1b86a71a-4ccc-11ea-90c9-b8763fc3091d
     * name=项目组
     * assinee=张三
     * create time=Tue Feb 11 20:43:30 CST 2020
     * executionId=1b7f7b27-4ccc-11ea-90c9-b8763fc3091d
     */
    @Test
    public void findMyTaskList() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String userId = "张三";
        //指定个人任务查询
        List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee(userId).list();

        for (Task task : list) {
            System.out.println("id=" + task.getId());
            System.out.println("name=" + task.getName());
            System.out.println("assinee=" + task.getAssignee());
            System.out.println("create time=" + task.getCreateTime());
            System.out.println("executionId=" + task.getExecutionId());
        }
    }

    /**
     * taskid = c19f0a12-4d4c-11ea-a07c-b8763fc3091d
     */
    @Test
    public void completeTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        String taskId = "c19f0a12-4d4c-11ea-a07c-b8763fc3091d";
        processEngine.getTaskService().complete(taskId);

        System.out.println("完成任务");

    }

    /**
     * 将组任务分配给个人任务，拾取任务
     */
    //由1个人去完成任务
    @Test
    public void claim() {
        //任务ID
        String taskId = "d086788b-4bc7-11ea-aece-b8763fc3091d";

        //分配的办理人
        String userId = "小B";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService().claim(taskId, userId);

    }

    /**
     * processInstance.getId() = 1b7f7b26-4ccc-11ea-90c9-b8763fc3091d  已完成流程没有
     * 82c4b6fa-4cab-11ea-9449-b8763fc3091d
     */
    @Test
    public void getHistoryActivityInstance() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        System.out.println("----------------------------------流程实例流转-----------------------");
        HistoryService historyService=processEngine.getHistoryService();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId("1b7f7b26-4ccc-11ea-90c9-b8763fc3091d")
                .finished().list();
        for (HistoricActivityInstance hai : list) {
            System.out.println("活动ID:" + hai.getId());
            System.out.println("流程实例ID:" + hai.getProcessInstanceId());
            System.out.println("活动名称：" + hai.getActivityName());
            System.out.println("办理人：" + hai.getAssignee());
            System.out.println("开始时间：" + hai.getStartTime());
            System.out.println("结束时间：" + hai.getEndTime());
            System.out.println("*************************************");
        }
    }

}
