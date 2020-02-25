package com.darryl.activiti.design_pattern.factory_pattern.complex;

/**
 * @Auther: Darryl
 * @Description: 超级工厂
 * @Date: created in 2020/2/25 20:56
 */

public class FactoryProducer {

    AbstractFactory getFactory(String type) {
        if ("SHAPE".equals(type)) {
            return new OtherShapeFactory();
        } else if ("COLOR".equals(type)) {
            return new ColorFactory();
        } else {
            return null;
        }
    }
}
