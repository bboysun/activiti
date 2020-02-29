package com.darryl.activiti.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: Darryl
 * @Description: atomic类型保证原子性
 * @Date: created in 2020/2/28 20:21
 */

public class AtomicLock {

    static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i=0; i<10000; i++){
                        atomicInteger.incrementAndGet();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("atomicint is " + atomicInteger);
    }

}
