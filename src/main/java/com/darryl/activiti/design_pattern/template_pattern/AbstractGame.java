package com.darryl.activiti.design_pattern.template_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/25 21:04
 */

public abstract class AbstractGame {
    abstract void init();
    abstract void startPlay();
    abstract void endPlay();
    abstract void goHome();

    final void paly() {
        // 初识化游戏
        init();
        // 开始游戏
        startPlay();
        // 结束游戏
        endPlay();
        // 回家
        goHome();
    }
}
