package com.darryl.activiti.lock.consumer_productor;

import org.springframework.util.StringUtils;

/**
 * @Auther: Darryl
 * @Description: 消费者
 * @Date: created in 2020/3/7 16:44
 */

public class Consumer implements Runnable {
    private String lock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (StringUtils.isEmpty(Clothes.color)) {
            System.out.println(Thread.currentThread().getName() + "，WAITING...");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "异常了");
                //e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "，RUNNABLE...");
        Clothes.color = "";
        lock.notify();
    }
}
