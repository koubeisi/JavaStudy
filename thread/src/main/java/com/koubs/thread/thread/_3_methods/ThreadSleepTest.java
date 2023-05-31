package com.koubs.thread.thread._3_methods;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author KouBeisi
 * @since 2021/6/16
 */
@Slf4j
public class ThreadSleepTest {

    @Test
    void test() throws InterruptedException {
        var t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        // RUNNABLE
        log.debug("t1 state:{}", t1.getState());

        // 主线程休眠 500ms 确保 t1 线程运行
        Thread.sleep(500);

        // TIMED_WAITING
        log.debug("t1 state:{}", t1.getState());
    }
}
