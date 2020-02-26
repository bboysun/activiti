package com.darryl.activiti.design_pattern.observer_pattern;

/**
 * @Auther: Darryl
 * @Description: 观察者模式的demo
 * @Date: created in 2020/2/26 13:25
 */

public class ObserverDemo {

    public static void main(String[] args) {
        SubjectService subject = new SubjectService();
        DarrylObserver darrylObserver = new DarrylObserver(subject);
        TomObserver tomObserver = new TomObserver(subject);

        System.out.println("1. 设置事件的state为17");
        subject.setState(17);

        System.out.println("2. 设置事件的state为19");
        subject.setState(19);

    }
}
