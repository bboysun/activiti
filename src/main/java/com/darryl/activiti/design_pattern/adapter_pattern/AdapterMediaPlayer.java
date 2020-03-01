package com.darryl.activiti.design_pattern.adapter_pattern;

/**
 * @Auther: Darryl
 * @Description: 能够适配高级播放器的适配器
 * @Date: created in 2020/3/1 19:43
 */

public class AdapterMediaPlayer implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer = new AdvancedMusicPlayer();

    @Override
    public void play(String fileName, String type) {
        if ("MP4".equals(type)) {
            advancedMediaPlayer.playMp4(fileName);
        } else if ("VLC".equals(type)) {
            advancedMediaPlayer.playVlc(fileName);
        }
    }
}
