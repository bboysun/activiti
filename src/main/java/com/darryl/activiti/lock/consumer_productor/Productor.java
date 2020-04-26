package com.darryl.activiti.lock.consumer_productor;

import org.springframework.util.StringUtils;

/**
 * @Auther: Darryl
 * @Description: 生产者
 * @Date: created in 2020/3/7 16:36
 */

public class Productor implements Runnable {

    private String lock;

    public Productor(String lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (!StringUtils.isEmpty(Clothes.color)) {
                System.out.println("***" + Thread.currentThread().getName() + "，WAITING...");
                try {
                    // 生产完了，就wait
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //准备生产
            System.out.println(Thread.currentThread().getName() + "，RUNNABLE...");
            Clothes.color = "red";
            lock.notify();
        }
    }
}
