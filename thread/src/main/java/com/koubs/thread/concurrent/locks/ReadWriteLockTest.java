package com.koubs.thread.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author KouBeisi
 * @since 2021/11/20
 */
public class ReadWriteLockTest {

    private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private static final Map<Integer, Object> map = new HashMap<>();

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    // 添加写锁
                    rwLock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() + " 正在写操作 " + j);
                    TimeUnit.MILLISECONDS.sleep(200);
                    map.put(j, j);
                    System.out.println(Thread.currentThread().getName() + " 写完了 " + j);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                } finally {
                    rwLock.writeLock().unlock();
                }
            }, String.valueOf(i)).start();
        }


        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(() -> {
                Object result = null;
                try {
                    // 添加读锁
                    rwLock.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + " 正在读操作 ");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    result = map.get(j);
                    System.out.println(Thread.currentThread().getName() + " 读完了 ");

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                } finally {
                    rwLock.readLock().unlock();
                }
                System.out.println(Thread.currentThread().getName() + " 读到了 " + result);
            }, String.valueOf(i)).start();
        }

    }
}
