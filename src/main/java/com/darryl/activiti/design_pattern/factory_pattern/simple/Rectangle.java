package com.darryl.activiti.design_pattern.factory_pattern.simple;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/25 20:30
 */

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("I am rectangle");
    }
}
