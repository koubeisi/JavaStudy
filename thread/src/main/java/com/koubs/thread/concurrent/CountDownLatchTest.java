package com.koubs.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 减少计数
 * 6个同学离开教室后，班长锁门
 * @author KouBeisi
 * @since 2021/11/19
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "号同学离开了教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" 班长锁门走人了");
    }
}
