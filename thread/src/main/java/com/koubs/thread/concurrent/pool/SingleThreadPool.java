package com.koubs.thread.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {

    public static void main(String[] args) {

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        for(int i=0;i<10;i++) {

            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }

            });
        }
        singleThreadPool.shutdown();
    }

}
