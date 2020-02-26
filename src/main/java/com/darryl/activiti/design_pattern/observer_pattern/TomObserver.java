package com.darryl.activiti.design_pattern.observer_pattern;

/**
 * @Auther: Darryl
 * @Description: 具体的观察者
 * @Date: created in 2020/2/26 13:20
 */

public class TomObserver extends ObserverService {

    public TomObserver(SubjectService subject) {
        this.subjectService = subject;
        this.subjectService.attach(this);
    }

    @Override
    void updateOperation() {
        System.out.println("Observer Tom here, subjectService's state is " + subjectService.getState());
    }
}
