package com.darryl.activiti.design_pattern.template_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/25 21:09
 */

public class FootballGame extends AbstractGame {

    @Override
    void init() {
        System.out.println("来到足球场");
    }

    @Override
    void startPlay() {
        System.out.println("开始踢足球");
    }

    @Override
    void endPlay() {
        System.out.println("结束踢足球");
    }

    @Override
    void goHome() {
        System.out.println("踢完足球回家");
    }
}
