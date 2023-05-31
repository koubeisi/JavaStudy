package com.koubs.thread.thread._1_create;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程方法之三
 * @author KouBeisi
 * @since 2021/6/16
 */
@Slf4j
public class CallableTest {

    @Test
    void test() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running...");
                Thread.sleep(1000);
                return 100;
            }
        });

        var thread = new Thread(task,"t1");
        thread.start();

        // 主线程会在此处阻塞
        final var integer = task.get();
        log.debug("{}",integer);
    }
}
