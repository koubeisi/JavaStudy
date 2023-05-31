package com.koubs.thread.thread._3_methods;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author KouBeisi
 * @since 2021/6/16
 */
@Slf4j
public class ThreadInterruptTest {

    @Test
    void test() throws InterruptedException {
        var t1 = new Thread(() -> {
            log.debug("enter sleep...");
            try {
                // 使用 JDK8 的方法可读性更好
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                log.debug("wake up...");
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
        t1.start();

        // 主线程休眠 500ms 确保 t1 线程运行
        Thread.sleep(500);
        log.debug("interrupt...");
        t1.interrupt();
    }
}
