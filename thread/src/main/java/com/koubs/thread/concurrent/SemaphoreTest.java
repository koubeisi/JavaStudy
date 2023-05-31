package com.koubs.thread.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯示例
 * @author KouBeisi
 * @since 2021/11/19
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                //抢占
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"获取到了信号");

                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName()+"释放了信号");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
