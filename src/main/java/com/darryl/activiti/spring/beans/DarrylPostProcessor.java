package com.darryl.activiti.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Auther: Darryl
 * @Description: spring bean 初始化前后有 beanpostProcessor前后会做一些事情
 * @Date: created in 2020/3/22 21:13
 */
//@Component
public class DarrylPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化之前做了一坨一坨的事情");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化完成之后做了一坨一坨的事情");
        return bean;
    }
}
