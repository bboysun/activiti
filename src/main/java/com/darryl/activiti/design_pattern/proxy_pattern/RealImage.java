package com.darryl.activiti.design_pattern.proxy_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/26 19:12
 */

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    @Override
    public void display() {
        System.out.println("显示图片：" + fileName);
    }

    private void loadFromDisk() {
        System.out.println("从磁盘中加载...");
    }
}
