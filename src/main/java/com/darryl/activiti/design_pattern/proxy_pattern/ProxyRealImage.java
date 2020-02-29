package com.darryl.activiti.design_pattern.proxy_pattern;

/**
 * @Auther: Darryl
 * @Description: 代理一个类的功能，可以对被代理的功能做一些控制，
 * 和装饰模式的区别，装饰模式是丰富了被装饰类的功能而已。实现上有可能差不多，但意义不太一样
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
