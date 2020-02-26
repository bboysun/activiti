package com.darryl.activiti.design_pattern.proxy_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/26 19:17
 */

public class ProxyRealImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyRealImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // 在调用被代理类方法前做了一些自己的控制
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
