package com.darryl.activiti.design_pattern.proxy_pattern;

/**
 * @Auther: Darryl
 * @Description: TODO:描述
 * @Date: created in 2020/2/26 19:20
 */

public class ProxyDemo {

    public static void main(String[] args) {
        ProxyRealImage proxyRealImage = new ProxyRealImage("123.txt");
        proxyRealImage.display();
        System.out.println();
        proxyRealImage.display();
    }

}
