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

    public static void main(String[] args) {
        final Cache cache = new Cache();

        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(()->{
                cache.put(j + "",j + "");
            },String.valueOf(i)).start();
        }


        for (int i = 0; i < 5; i++) {
            int j = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 读到了 " + cache.get(j + ""));
            },String.valueOf(i)).start();
        }

    }
}


class Cache{

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private Map<String,Object> map = new HashMap<>();

    public void put(String key,String value){

        try {
            // 添加写锁
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+" 正在写操作 " + value);
            TimeUnit.MILLISECONDS.sleep(200);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+" 写完了 " + value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Object get(String key){
        Object result = null;
        try {
            // 添加读锁
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+" 正在读操作 ");
            TimeUnit.MILLISECONDS.sleep(1000);
            result=map.get(key);
            System.out.println(Thread.currentThread().getName()+" 读完了 ");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return result;
    }
}
