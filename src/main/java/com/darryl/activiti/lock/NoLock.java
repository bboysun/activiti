package com.darryl.activiti.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: Darryl
 * @Description: 此时多线程下，由于没有任何锁是不安全的，count结果不会得到我们预期的200；
 * @Date: created in 2020/2/28 19:49
 */

public class NoLock {

    static int count;
    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i=0; i<10000; i++) {
                        count++;
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
