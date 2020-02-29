package com.darryl.activiti.design_pattern.observer_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/26 13:21
 */

public class DarrylObserver extends ObserverService {

    public DarrylObserver(SubjectService subject) {
        this.subjectService = subject;
        this.subjectService.attach(this);
    }

    @Override
    void updateOperation() {
        System.out.println("observer Darryl here, subjectService's state is " + subjectService.getState());
    }
}
