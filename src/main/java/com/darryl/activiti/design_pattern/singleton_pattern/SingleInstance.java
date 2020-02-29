package com.darryl.activiti.design_pattern.singleton_pattern;

/**
 * @Auther: Darryl
 * @Description: 单例
 * @Date: created in 2020/2/25 19:53
 */

public class SingleInstance {

    //=======================================饿汉式=====================
   /* private static SingleInstance instance = new SingleInstance();

    private SingleInstance(){}

    public static SingleInstance getInstance() {
        return instance;
    }*/
    //=================================================================


    //=======================================懒汉式1=====================
    /*private static SingleInstance instance;
    private SingleInstance() {}
    public static SingleInstance getInstance() {
        if (instance == null) {
            instance = new SingleInstance();
        }
        return instance;
    }*/
    //=======================================懒汉式1=====================


    //=======================================懒汉式2=====================
    /*private static SingleInstance instance;
    private SingleInstance() {}
    public static synchronized SingleInstance getInstance() {
        if (instance == null) {
            instance = new SingleInstance();
        }
        return instance;
    }*/
    //=======================================懒汉式2=====================

    //=======================================懒汉式3=====================
    /*private static SingleInstance instance;
    private SingleInstance() {
    }
    public static SingleInstance getInstance() {
        if (instance == null) {
            synchronized (SingleInstance.class) {
                if (instance == null) {
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }*/
    //=======================================懒汉式3=====================

    //=======================================懒汉式3=====================
    private static class Holder {
        private static final SingleInstance instance = new SingleInstance();
    }
    private SingleInstance() {}

    public static SingleInstance getInstance() {
        return Holder.instance;
    }
    //=======================================懒汉式3=====================

}
