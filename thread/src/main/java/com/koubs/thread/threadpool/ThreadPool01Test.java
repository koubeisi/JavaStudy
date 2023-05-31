package com.koubs.thread.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 创建两个单元测试方式，比较手动创建线程和使用线程池创建线程的性能
 * @author KouBeisi
 * @date 2021/5/14
 */
@Slf4j
class ThreadPool01Test {

    private final Random random = SecureRandom.getInstanceStrong();

    ThreadPool01Test() throws NoSuchAlgorithmException {
    }

    @Test
    void test01() throws InterruptedException {
        var start = System.currentTimeMillis();
        var list = new ArrayList<Integer>(100000);
        var count = 10000;
        for (var i = 1; i <= count; i++) {
            var thread = new Thread(() -> list.add(random.nextInt()));
            thread.start();
            thread.join();
        }

        log.info("Time: {}", (System.currentTimeMillis() - start));
        log.info("Size: {}", list.size());
    }


    @Test
    void test02() throws InterruptedException {
        var executor = Executors.newSingleThreadExecutor();
        var start = System.currentTimeMillis();
        var list = new ArrayList<Integer>(100000);
        var count = 10000;
        for (var i = 0; i < count; i++) {
            executor.execute(() -> list.add(random.nextInt()));
        }
        executor.shutdown();
        boolean termination = executor.awaitTermination(1, TimeUnit.HOURS);
        if (termination) {
            log.info("Time: {}", (System.currentTimeMillis() - start));
            log.info("Size: {}", list.size());
        }

    }


}
