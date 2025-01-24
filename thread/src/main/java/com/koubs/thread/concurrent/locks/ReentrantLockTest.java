package com.koubs.thread.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author KouBeisi
 */
class ReentrantLockTest {

    private final ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            System.out.println("开始往buff里写数据");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("终于写完了");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("写线程被中断");
        } finally {
            lock.unlock();
        }


    }

    public void read() {
        //这里的锁可以相应中断
        try {
            lock.lockInterruptibly();
            System.out.println("从buff中读数据");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("终于读完了");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("中断读线程");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest t = new ReentrantLockTest();

        Thread writer = new Thread(t::write, "Writer");
        Thread reader = new Thread(t::read, "Reader");

        writer.start();
        reader.start();

        // 模拟读线程等待n秒后中断读线程
        try {
            // 设置2秒表示读线程未获取到锁，则中断读线程
            // 设置7秒表示读线程获取到锁，则读线程读完数据后，中断就不起作用了
            Thread.sleep(2000);
            reader.interrupt();  // 模拟中断读线程
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("当前主线程被中断");
        }
    }

}
