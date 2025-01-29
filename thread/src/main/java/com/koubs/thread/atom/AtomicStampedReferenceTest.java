package com.koubs.thread.atom;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author KouBeisi
 * @since 2025/1/29
 */
class AtomicStampedReferenceTest {


    @Test
    void test() throws InterruptedException {
        int initialValue = 100;
        int initialStamp = 0;
        AtomicStampedReference<Integer> atomicRef =
                new AtomicStampedReference<>(initialValue, initialStamp);
        // 更新成功后，值 = 101, 版本号 = 1
        var thread1 = new Thread(() -> {
            int[] stampHolder = new int[1];
            Integer oldValue = atomicRef.get(stampHolder);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
            // oldValue = 100, oldStamp = 0
            boolean success = atomicRef.compareAndSet(oldValue, 101, stampHolder[0], stampHolder[0] + 1);
            System.out.println("Thread1:" + (success ? "更新成功" : "更新失败"));
        });
        thread1.start();

        var thread2 = new Thread(() -> {
            int[] stampHolder = new int[1];
            Integer oldValue = atomicRef.get(stampHolder);
            // oldValue = 100, oldStamp = 0
            boolean success = atomicRef.compareAndSet(oldValue, 102, stampHolder[0], stampHolder[0] + 1);
            System.out.println( "Thread2:" + (success ? "更新成功" : "更新失败"));
        });
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(atomicRef.getReference());
    }
}
