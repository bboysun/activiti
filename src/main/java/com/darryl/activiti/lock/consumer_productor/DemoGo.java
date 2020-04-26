package com.darryl.activiti.lock.consumer_productor;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 生产者消费者模型，多个线程争抢一个资源会出现假死现象
 * @Date: created in 2020/3/7 16:49
 */

public class DemoGo {
    public static void main(String[] args) {
        List<Thread> productors = Lists.newArrayList();
        List<Thread> consumers = Lists.newArrayList();
        String lock = "lock";
        Clothes clothes = new Clothes();
        for (int i = 0; i < 10; i++) {
            Productor productor = new Productor(lock);
            Consumer consumer = new Consumer(lock);

            Thread pThread = new Thread(productor);
            Thread cThread = new Thread(consumer);

            pThread.setName("生产者" + i);
            cThread.setName("消费者" + i);

            productors.add(pThread);
            consumers.add(cThread);

            pThread.start();
            cThread.start();
        }
        try {
            Thread.sleep(10000);
            for (int i=0; i<10; i++) {
                System.out.println(productors.get(i).getName() + "状态：" +
                        productors.get(i).getState());
                System.out.println(consumers.get(i).getName() + "状态：" + consumers.get(i).getState());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
