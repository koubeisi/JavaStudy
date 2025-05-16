package com.koubs.thread.keywords.sync.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author KouBeisi
 * @since 2025/5/12
 */
public class SynchronizedTest {

    private int i = 0;

    public synchronized void add() {
        i++;
    }

    private final AtomicInteger j = new AtomicInteger(0);

    private final LongAdder  k = new LongAdder();
    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        var start1 = System.currentTimeMillis();
        var count1 = new CountDownLatch(5000);
        for (int i = 0; i < 5000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    test.add();
                }
                count1.countDown();
            }).start();
        }
        count1.await();
        System.out.println(System.currentTimeMillis() - start1);
        System.out.println(test.i);

        var start2 = System.currentTimeMillis();
        var count2 = new CountDownLatch(5000);
        for (int i = 0; i < 5000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    test.j.incrementAndGet();
                }
                count2.countDown();
            }).start();
        }
        count2.await();
        System.out.println(System.currentTimeMillis() - start2);
        System.out.println(test.j.get());

        var start3 = System.currentTimeMillis();
        var count3 = new CountDownLatch(5000);
        for (int i = 0; i < 5000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    test.k.add(1);
                }
                count3.countDown();
            }).start();
        }
        count3.await();
        System.out.println(System.currentTimeMillis() - start3);
        System.out.println(test.k.sum());
    }
}
