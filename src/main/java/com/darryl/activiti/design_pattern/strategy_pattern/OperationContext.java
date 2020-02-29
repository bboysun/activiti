package com.darryl.activiti.design_pattern.strategy_pattern;

/**
 * @Auther: Darryl
 * @Description: 使用过程中，通过构造函数的strategy入参选择不同策略
 * @Date: created in 2020/2/26 12:58
 */

public class OperationContext {

    private Strategy strategy;

    public OperationContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeOperation(int num1, int num2) {
        strategy.doOperation(num1, num2);
    }
}
