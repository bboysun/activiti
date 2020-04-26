package com.darryl.activiti.lock.consumer_productor;

/**
 * @Auther: Darryl
 * @Description: 多个生产线和购买用户来处理公交车，简单实现一个多个线程假死的现象。
 * @Date: 2020/04/26
 */
public class Rocket {
	// 公交车的数量
	private int count = 0;
	// lock
	private final Object LOCK = new Object();
	// 判断是否已经生产的标志位
	private volatile boolean isProduct = false;

	// 消费者
	public void consume() throws InterruptedException {
		synchronized (LOCK) {
			if (isProduct) {
				System.out.println("消费者" + Thread.currentThread().getName() + "购买了一枚Rocket编号【" + count + "】");
				isProduct = false;
				LOCK.notify();
			} else {
				LOCK.wait();
			}
		}
	}

	// 生产者
	public void produce() throws InterruptedException {
		synchronized (LOCK) {
			if (isProduct) {
				LOCK.wait();
			} else {
				System.out.println("生产者" + Thread.currentThread().getName() + "生产了一枚Rocket编号【"  + ++count + "】");
				isProduct = true;
				LOCK.notify();
			}
		}
	}

	public static void main(String[] args) {
		Rocket rocket = new Rocket();
		// 不停的生产火箭
		for (int i = 0; i < 10; i++) {
			new Thread(){
				@Override
				public void run() {
					try {
						rocket.produce();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		// 不停的消费火箭
		for (int i = 0; i < 10; i++) {
			new Thread() {
				@Override
				public void run() {
					try {
						rocket.consume();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
}
