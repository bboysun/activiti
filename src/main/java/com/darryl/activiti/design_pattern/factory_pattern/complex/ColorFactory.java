package com.darryl.activiti.design_pattern.factory_pattern.complex;

import com.darryl.activiti.design_pattern.factory_pattern.simple.Shape;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/25 20:52
 */

public class ColorFactory extends AbstractFactory {

    @Override
    Shape getShape(String type) {
        return null;
    }

    @Override
    Color getColor(String type) {
        if ("RED".equals(type)) {
            return new Red();
        } else if ("GREEN".equals(type)) {
            return new Green();
        } else if ("BLUE".equals(type)) {
            return new Blue();
        } else {
            return null;
        }
    }
}
