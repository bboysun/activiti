package com.darryl.activiti.workflow.flow1;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @Auther: Darryl
 * @Description: 工作流1
 * @Date: created in 2020/2/9 20:58
 */

public class WorkFlow1 {

    @Autowired
    private static RepositoryService repositoryService;
    
    @Autowired
    private static ResourceLoader resourceLoader;

    @PostConstruct
    public void initProcess() throws IOException {
        DeploymentBuilder deploymentBuilder= repositoryService.createDeployment();
        deploymentBuilder.enableDuplicateFiltering().addClasspathResource("test.bpmn").name("myProcess_1").deploy();
        Resource resource1 = resourceLoader.getResource("classpath:/processes/test.bpmn"); //加载流程图资源文件
        deploymentBuilder.enableDuplicateFiltering().addInputStream(resource1.getFilename(), resource1.getInputStream()).name("myProcess_1").deploy(); //按流程id部署
    }




}
