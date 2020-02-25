package com.darryl.activiti.design_pattern.factory_pattern.simple;

/**
 * @Auther: Darryl
 * @Description: 简单工厂模式-形状工厂
 * @Date: created in 2020/2/25 20:30
 */

public class ShapeFactory {

    public Shape getInstance(String type) {
        if ("CIRCLE".equals(type)) {
            return new Circle();
        } else if ("RECTANGLE".equals(type)) {
            return new Rectangle();
        } else if ("Square".equals(type)) {
            return new Square();
        } else {
            return null;
        }
    }

}
