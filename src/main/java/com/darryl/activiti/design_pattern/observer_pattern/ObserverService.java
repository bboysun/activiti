package com.darryl.activiti.design_pattern.observer_pattern;

/**
 * @Auther: Darryl
 * @Description: 观察者抽象类
 * @Date: created in 2020/2/26 13:14
 */

public abstract class ObserverService {
    protected SubjectService subjectService;
    abstract void updateOperation();
}
