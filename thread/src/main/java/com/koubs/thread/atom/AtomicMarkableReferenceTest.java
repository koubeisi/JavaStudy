package com.koubs.thread.atom;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author KouBeisi
 * @since 2025/1/29
 */
class AtomicMarkableReferenceTest {


    @Test
    void test() throws InterruptedException {
        int initialValue = 100;
        boolean initialMark = false;
        var atomicRef = new AtomicMarkableReference<>(initialValue, initialMark);
        // 更新成功后，值 = 101, 版本号 = 1
        var thread1 = new Thread(() -> {
            var markHolder = new boolean[1];
            Integer oldValue = atomicRef.get(markHolder);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
            // oldValue = 100, oldStamp = 0
            boolean success = atomicRef.compareAndSet(oldValue, 101, markHolder[0], !markHolder[0]);
            System.out.println("Thread1:" + (success ? "更新成功" : "更新失败"));
        });
        thread1.start();

        var thread2 = new Thread(() -> {
            boolean[] markHolder = new boolean[1];
            Integer oldValue = atomicRef.get(markHolder);
            // oldValue = 100, oldStamp = 0
            boolean success = atomicRef.compareAndSet(oldValue, 102, markHolder[0], !markHolder[0]);
            System.out.println( "Thread2:" + (success ? "更新成功" : "更新失败"));
        });
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(atomicRef.getReference());

    }

}
