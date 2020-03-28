package com.darryl.activiti.lock;

/**
 * @Auther: Darryl
 * @Description: 在很多情况下，主线程创建并启动子线程，如果子线程中要进行大量的耗时运算，主线程往往将
 *               早于子线程结束之前结束。这时，如果主线程想要等待子线程执行完成以后再结束，那就要用到join的方法。
 *               join方法会使所属线程正常执行run方法，而使当前线程进行无限期阻塞，直到join所属线程销毁后，
 *               当前线程才会继续执行。
 * @Date: created in 2020/3/7 16:06
 */

public class JoinThread implements Runnable {

    int count;

    @Override
    public void run() {
        for (int i=0; i<90000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        JoinThread joinThread = new JoinThread();
        Thread thread = new Thread(joinThread);
        thread.start();
        // 可以对比去掉join和添加join的不同
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我想要前面线程的总和：" + joinThread.count);
    }
}
