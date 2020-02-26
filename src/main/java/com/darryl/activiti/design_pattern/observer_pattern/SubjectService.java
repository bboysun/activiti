package com.darryl.activiti.design_pattern.observer_pattern;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 一个主题事件，需要有绑定观察者方法，通知观察者的方法扥，当事件的state发生变化时，
 * 会通知观察者做相应的处理
 * @Date: created in 2020/2/26 13:15
 */

public class SubjectService {

    private List<ObserverService> observerList = Lists.newArrayList();

    private int state;

    public void attach(ObserverService observer) {
        observerList.add(observer);
    }

    public void notifyObserver() {
        for (ObserverService observer : observerList) {
            observer.updateOperation();
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObserver();
    }
}
