package com.darryl.activiti.proxy;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/3/18 20:52
 */

public class SnowImpl implements Snow {

    @Override
    public void color(String color) {
        System.out.println(color + "的雪花");
    }

    @Override
    public void shape(String shape) {
        System.out.println(shape + "的雪花");
    }
}
