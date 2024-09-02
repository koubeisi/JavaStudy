package com.koubs.thread.concurrent.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author KouBeisi
 */
class ReentrantLockTest {

    private final ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往buff里写数据");
            while (true) {
                if ((System.currentTimeMillis() - startTime) > Integer.MAX_VALUE) {
                    break;
                }
            }
            System.out.println("终于写完了");
        } finally {
            lock.unlock();
        }


    }

    public void read() throws InterruptedException {
        //这里的锁可以相应中断
        lock.lockInterruptibly();
        try {
            System.out.println("从buff中读数据");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest t = new ReentrantLockTest();
        t.write();
        try {
            t.read();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
