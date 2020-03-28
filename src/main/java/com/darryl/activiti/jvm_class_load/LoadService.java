package com.darryl.activiti.jvm_class_load;

import com.darryl.activiti.design_pattern.proxy_pattern.ProxyDemo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Darryl
 * @Description: 类加载过程：   验证（合法性）-》准备（分配内存空间）
 * -》解析—》初始化（对静态变量进行初始化值）-》使用-》销毁
 * @Date: created in 2020/2/26 21:31
 */

public class LoadService {
    // 静态变量，静态代码块，静态方法都是在当前类被主动调用的时候，类加载过程中的初始化阶段加载在JVM中的
    // 加载到JVM的metaspace区域
    private static int c;
    // 类的全局变量，是在当前类使用过程中new出一个对象实例时初始化的，
    // 这些变量是类相关的信息，是存在jvm堆内存区域的
    private int a;
    private String str;

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("darryl");

        ArrayList<String> arrayList = new ArrayList<>(4);
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        System.out.println(arrayList.size());
        arrayList.add("5");
        System.out.println(arrayList.size());




        // 方法中的局部变量是存储在jvm虚拟机栈中的，每个方法在虚拟机栈中有个栈帧，这个变量就在栈帧中存储
        // 需要手动初始化值
        int b = 3;
        LoadService demo = new LoadService();
        // 类的全局变量会初始化值，全局变量的默认的初始化值是在类加载的准备阶段进行的
        System.out.println("a is " + demo.a + ", str is " + demo.str);
        System.out.println("c is " + c);
        // 如果b没有初始化值会报错的
        System.out.println("b is " + b);
        int res = 1<<30;
        int res1 = 1<<4;
        System.out.println("1<<30 = " + res);
        System.out.println("1<<4 = " + res1);

        HashMap<String, String> hashMap = Maps.newHashMap();
        Map map = Collections.synchronizedMap(hashMap);

        hashMap.put(null, "空");
        System.out.println(hashMap.get(null));

        Hashtable<String, String> hashTable = new Hashtable<>();
        //hashTable.put(null, "kong");
        hashTable.put("kong", null);
        System.out.println(hashTable.get("kong"));

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    }
}
