package com.darryl.activiti.design_pattern.template_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/25 21:07
 */

public class BasketballGame extends AbstractGame {

    @Override
    void init() {
        System.out.println("来到篮球场");
    }

    @Override
    void startPlay() {
        System.out.println("开始打篮球");
    }

    @Override
    void endPlay() {
        System.out.println("结束打篮球");
    }

    @Override
    void goHome() {
        System.out.println("打完篮球回家");
    }
}
