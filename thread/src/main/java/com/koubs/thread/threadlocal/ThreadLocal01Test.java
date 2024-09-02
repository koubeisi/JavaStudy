package com.koubs.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 多线程并发情况下访问同一变量
 * @author KouBeisi
 * @date 2021/5/14
 */
@Slf4j
class ThreadLocal01Test {

    private String content;

    /**
     * 多线程并发访问变量，线程不安全
     */
    @Test
    void test() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        final int count = 5;
        for (int i = 0; i < count; i++) {
            executorService.execute(() -> {
                content = Thread.currentThread().getName() + "的文章";
                log.info("{}:{}",Thread.currentThread().getName(),content);
            });
        }
        executorService.shutdown();
        boolean result = executorService.awaitTermination(20, TimeUnit.SECONDS);
        if (result) {
            log.info("线程结束");
        }
    }


    /**
     * 使用 synchronized 加锁，使线程安全
     */
    @Test
    void synchronizedTest() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        final int count = 5;
        for (int i = 0; i < count; i++) {
            executorService.execute(() -> {
                synchronized(ThreadLocal01Test.class){
                    content = (Thread.currentThread().getName() + "的文章");
                    log.info("{}:{}",Thread.currentThread().getName(),content);
                }
            });
        }
        executorService.shutdown();
        boolean result = executorService.awaitTermination(20, TimeUnit.SECONDS);
        if (result) {
            log.info("线程结束");
        }
    }
}
