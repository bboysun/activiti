package com.darryl.activiti.lock;

import org.springframework.util.StopWatch;

/**
 * @Auther: Darryl
 * @Description: yield() 线程方法作用是放弃当前的CPU资源，将它让给其他的任务去占用
 *               CPU执行时间，但放弃的时间不确定，有可能刚放弃就马上又获得CPU时间片。
 * @Date: created in 2020/3/7 15:56
 */

public class YieldThread implements Runnable {

    int count;

    @Override
    public void run() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i=0; i<50000; i++) {
            count++;
            // yield()方法会将当前线程的CPU资源放弃掉，让给其他线程操作，放弃时间不确定
            Thread.yield();
        }
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println(totalTimeMillis);
    }

    public static void main(String[] args) {
        new Thread(new YieldThread()).start();
    }
}
