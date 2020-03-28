package com.darryl.activiti.spring.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: Darryl
 * @Description: spring bean 的初始化
 * @Date: created in 2020/3/22 21:04
 */
@Component
public class Bus {
    @PostConstruct
    public void init() {
        System.out.println("init 初始化");
    }

    public Bus() {
        System.out.println("构造函数");
    }

    public void drive() {
        System.out.println("开车走起。。");
    }

}
