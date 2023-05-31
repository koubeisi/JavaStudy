package com.koubs.thread.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 该程序用以观察不同线程池下线程运行的效率
 * 注意：使用 Test 时，线程池会自动关闭，因此需要调用线程池的 awaitTermination 方法
 *
 * @author KouBeisi
 * @date 2021/5/14
 */
@Slf4j
class ThreadPool02Test {

    @Test
    void testCachedThreadPool() throws InterruptedException {
        var start = System.currentTimeMillis();
        var executorService1 = Executors.newCachedThreadPool();

        final var count = 100;
        for (var i = 1; i <= count; i++) {
            executorService1.execute(new Count(i));
        }

        executorService1.shutdown();
        var termination = executorService1.awaitTermination(1, TimeUnit.HOURS);
        if (termination) {
            log.info("Test01 time:{}", (System.currentTimeMillis() - start));
        }
    }

    @Test
    void testFixedThreadPool() throws InterruptedException {
        var start = System.currentTimeMillis();

        var executorService2 = Executors.newFixedThreadPool(10);

        final var count = 100;
        for (var i = 1; i <= count; i++) {
            executorService2.execute(new Count(i));
        }

        executorService2.shutdown();
        var termination = executorService2.awaitTermination(1, TimeUnit.HOURS);
        if (termination) {
            log.info("Test02 time:{}", (System.currentTimeMillis() - start));
        }
    }

    @Test
    void testSingleThreadPool() throws InterruptedException {
        var start = System.currentTimeMillis();

        var executorService3 = Executors.newSingleThreadExecutor();

        final var count = 100;
        for (var i = 1; i <= count; i++) {
            executorService3.execute(new Count(i));
        }

        executorService3.shutdown();
        var termination = executorService3.awaitTermination(1, TimeUnit.HOURS);
        if (termination) {
            log.info("Test03 time:{}", (System.currentTimeMillis() - start));
        }
    }
}

@Slf4j
class Count implements Runnable {

    int i;

    public Count(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        log.info("{}--{}", Thread.currentThread().getName(), i);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
