package com.darryl.activiti.design_pattern.adapter_pattern;

/**
 * @Auther: Darryl
 * @Description: 高级音乐播放器
 * @Date: created in 2020/3/1 19:36
 */
public class AdvancedMusicPlayer implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String fileName) {
        System.out.println("MP4 : player is playing " + fileName);
    }

    @Override
    public void playVlc(String fileName) {
        System.out.println("VLC : player is playing " + fileName);
    }
}
