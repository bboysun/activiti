package com.darryl.activiti.spring.scope;

import com.darryl.activiti.spring.scope.config.BossConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: Darryl
 * @Description: 配置boss类的scope，根据equals方法来验证是否是同一个实例
 * @Date: created in 2020/3/18 22:52
 */

public class BossMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext actx = new AnnotationConfigApplicationContext(BossConfig.class);
        Boss boss1 = actx.getBean(Boss.class);
        Boss boss2 = actx.getBean(Boss.class);

        System.out.println("boss equals : " + boss1.equals(boss2));
    }
}
