package com.koubs.thread.thread._3_methods;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author KouBeisi
 */
@Slf4j
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {

        final var i = new int[]{0};

        var thread1 = new Thread(() -> {
            log.debug("开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            i[0] = 10;
        });
        thread1.start();
        // 调用 thread1 的 join 方法后，主线程会等待线程 thread1 运行结束后才继续执行
        thread1.join();
        log.debug("End:{}", i[0]);

    }

}
