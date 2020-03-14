package com.darryl.activiti.thread;

/**
 * @Auther: Darryl
 * @Description: threadlocal的demo学习
 * @Date: created in 2020/3/11 20:25
 */

public class ThreadLocalDemo {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(1);
                System.out.println("执行各种操作。。。");
                System.out.println(threadLocal.get());
                int count = threadLocal.get();
                threadLocal.set(count + 5);
                System.out.println("接着一顿操作。。。");
                System.out.println(threadLocal.get());
                // 一定要记得remove掉，避免内存泄漏
                threadLocal.remove();
            }
        }).start();

        System.out.println("main get : " + threadLocal.get());
    }

}
