package com.koubs.thread.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可中断测试
 * @author KouBeisi
 * @since 2021/11/10
 */
public class LockInterruptedTest {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable run = ()->{
            boolean lock = false;
                try {
                    System.out.println(Thread.currentThread().getName() + " 进入同步代码块");
                    lock = LOCK.tryLock(3000, TimeUnit.MILLISECONDS);
                    Thread.sleep(200000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                } finally {
                    if (lock){
                        LOCK.unlock();
                    }

                }
        };

        final Thread thread1 = new Thread(run);
        thread1.start();
        final Thread thread2 = new Thread(run);
        thread2.start();
        // 中断线程2
        thread2.interrupt();

        Thread.sleep(1000);
        // 获取线程的状态
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
    }
}
