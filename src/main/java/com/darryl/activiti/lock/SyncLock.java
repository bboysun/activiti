package com.darryl.activiti.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: Darryl
 * @Description: sync 同步锁保证线程安全
 * @Date: created in 2020/2/28 20:00
 */

public class SyncLock {

    static int count;

    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        synchronized (SyncLock.class) {
                            count++;
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count is " + count);
    }

}
