package com.darryl.activiti.design_pattern.strategy_pattern;

/**
 * @Auther: Darryl
 * @Description: 策略模式的demo
 * @Date: created in 2020/2/26 13:01
 */

public class StrategyDemo {

    public static void main(String[] args) {
       OperationContext contextAdd = new OperationContext(new OperationAdd());
       contextAdd.executeOperation(7, 9);

       OperationContext contextMultiply = new OperationContext(new OperationMultiply());
       contextMultiply.executeOperation(2, 9);
    }
}
