package com.darryl.activiti.design_pattern.factory_pattern.complex;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/25 20:48
 */

public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("I am red");
    }
}
