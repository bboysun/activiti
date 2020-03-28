package com.darryl.activiti.design_pattern.adapter_pattern;

/**
 * @Auther: Darryl
 * @Description: 简单播放器，现在他需要适配高级播放器的功能
 * @Date: created in 2020/3/1 19:33
 */
public class AutoMediaPlayer implements MediaPlayer {

    private AdapterMediaPlayer adapterMediaPlayer = new AdapterMediaPlayer();

    @Override
    public void play(String fileName, String type) {
        if ("MP3".equals(type)) {
            System.out.println(type + " : auto media player is playing " + fileName);
        } else if ("MP4".equals(type) || "VLC".equals(type)) {
            adapterMediaPlayer.play(fileName, type);
        } else {
            System.out.println("no support type");
        }
    }
}
