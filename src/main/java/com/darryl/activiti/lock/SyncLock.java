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

    // static synchronized表明所有该类的实例会公用一个监视器，
    // 也就是该类的所有实例对象在执行func()方法都会是同步的。
    public static synchronized void func(){
        count++;
    }

    // synchronized表明不同实例对象，该方法是不会同步的。
    public synchronized void func2(){
        count++;
    }

    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            SyncLock syncLock = new SyncLock();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        //1. 会同步
                        //func();
                        //2. 不会同步
                        syncLock.func2();
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
