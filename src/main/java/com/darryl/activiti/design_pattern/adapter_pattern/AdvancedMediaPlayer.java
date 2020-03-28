package com.darryl.activiti.design_pattern.adapter_pattern;

/**
 * @Auther: Darryl
 * @Description: 高级播放器接口
 * @Date: created in 2020/3/1 19:35
 */
public interface AdvancedMediaPlayer {
    void playMp4(String fileName);
    void playVlc(String fileName);
}
