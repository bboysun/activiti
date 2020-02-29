package com.darryl.activiti.design_pattern.strategy_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/26 12:56
 */

public class OperationMultiply implements Strategy {
    @Override
    public void doOperation(int num1, int num2) {
        System.out.println("multiply: " + num1 + " * " + num2);
    }
}
