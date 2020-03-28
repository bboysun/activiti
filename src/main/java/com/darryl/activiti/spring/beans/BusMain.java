package com.darryl.activiti.spring.beans;

import com.darryl.activiti.spring.beans.config.BusConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: Darryl
 * @Description: 测试spring bean声明周期中的的初始化方法之前能做什么动作
 * @Date: created in 2020/3/22 21:10
 */

public class BusMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BusConfig.class);
        Bus bus = (Bus)context.getBean("bus");
        bus.drive();
    }
}
