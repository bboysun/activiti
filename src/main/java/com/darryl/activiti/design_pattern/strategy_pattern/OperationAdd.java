package com.darryl.activiti.design_pattern.strategy_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/26 12:54
 */

public class OperationAdd implements Strategy {

    @Override
    public void doOperation(int num1, int num2) {
        System.out.println("add: " + num1 + " + " + num2);
    }
}
