package com.darryl.activiti.activit_api;

/**
 * @Auther: Darryl
 * @Description: 工作流引擎（单例），目前不需要
 * @Date: created in 2020/2/17 9:40
 */
@Deprecated
public class ActivitiEngine {

    private ActivitiEngine() {}

    public ActivitiEngine getInstance() {
        return Inner.engine;
    }

    private static class Inner {
        private static ActivitiEngine engine = new ActivitiEngine();
    }

}
