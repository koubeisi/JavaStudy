package com.koubs.thread.keywords.sync.lock;

/**
 * @author KouBeisi
 * @since 2025/5/12
 */
public class SynchronizedTest {

    private int i = 0;

    public synchronized void add() {
        i++;
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    test.add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + ":" + test.i);
    }
}
