package com.darryl.activiti.lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Auther: Darryl
 * @Description: CAS乐观锁
 * @Date: created in 2020/2/28 20:44
 */

public class CasLock implements Runnable{

    static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) {
        CasLock casLock = new CasLock();
        new Thread(casLock).start();
        new Thread(casLock).start();
    }

    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName() + " flag is " + flag.get());
        if (flag.compareAndSet(true, false)) {
            System.out.println("**Thread name: " + Thread.currentThread().getName() + " flag set " + flag.get());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.set(true);
        } else {
            System.out.println("重试机制, Thread name: " + Thread.currentThread().getName() + " flag is " + flag.get());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 需要不停地重试，直到成功
            run();
        }
    }
}
