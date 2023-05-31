package com.koubs.thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏示例
 * 集齐 7 颗龙珠和召唤龙珠
 * @author KouBeisi
 * @since 2021/11/19
 */
public class CyclicBarrierTest {

    private static final int NUMBER = 7;

    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("所有的屏障点都已经到达");
        });

        // 集齐七颗龙珠过程
        for (int i = 0; i < NUMBER; i++) {
            new Thread(() -> {
                try {
                    System.out.println("第" + Thread.currentThread().getName() +  "个线程的屏障点已到达");
                    cyclicBarrier.await();
                    System.out.println("继续执行第" + Thread.currentThread().getName() + "个线程");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }


}
