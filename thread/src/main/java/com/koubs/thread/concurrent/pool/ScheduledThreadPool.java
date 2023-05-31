package com.koubs.thread.concurrent.pool;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        /**
                  * 一下三个函数分别单独输出的结果
         */

        basicSchedule();
        //输出结果为：
        //main 04:47:14
        //pool-1-thread-2 04:47:16
        //pool-1-thread-3 04:47:16
        //pool-1-thread-1 04:47:16
        //pool-1-thread-2 04:47:16
        //pool-1-thread-3 04:47:16
        //pool-1-thread-1 04:47:16
        //pool-1-thread-2 04:47:16
        //pool-1-thread-3 04:47:16
        //pool-1-thread-2 04:47:16
        //pool-1-thread-1 04:47:16


        scheduledWithFixedDelay();
        //输出结果为：
        //main 04:48:30
        //pool-1-thread-1 04:48:31
        //pool-1-thread-1 04:48:33
        //pool-1-thread-2 04:48:35
        //pool-1-thread-2 04:48:37
        //pool-1-thread-2 04:48:39
        //pool-1-thread-2 04:48:41
        //pool-1-thread-2 04:48:43
        //pool-1-thread-2 04:48:45


        scheduledAtFiexRate();
        //输出结果为：
        //main 04:50:34
        //pool-1-thread-1 04:50:35
        //pool-1-thread-1 04:50:37
        //pool-1-thread-1 04:50:39
        //pool-1-thread-1 04:50:41
        //pool-1-thread-1 04:50:43
        //pool-1-thread-1 04:50:45
        //pool-1-thread-1 04:50:47
        //pool-1-thread-1 04:50:49
        //pool-1-thread-1 04:50:51
    }

    private static void basicSchedule() {

        for(int i=0;i<10;i++) {
            //延迟2秒后建立线程
            scheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
                }

            }, 2, TimeUnit.SECONDS);
        }

    }

    private static void scheduledWithFixedDelay() {

        //延迟2秒后建立线程，每个2秒新建立一个线程
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            }

        }, 1, 2, TimeUnit.SECONDS);
    }

    private static void scheduledAtFiexRate() {

        //延迟2秒后建立线程，每个2秒新建立一个线程
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            }

        }, 1, 2, TimeUnit.SECONDS);
    }

}
