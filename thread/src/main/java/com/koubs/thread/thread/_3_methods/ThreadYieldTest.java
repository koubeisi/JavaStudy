package com.koubs.thread.thread._3_methods;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 由于 t1 线程调用了 yield 方法，让出了 CPU <br>
 * 因此 t1 线程的 count 增长速度比 t2 快
 * @author KouBeisi
 * @since 2021/6/16
 */
@Slf4j(topic = "ddd")
public class ThreadYieldTest {

    @Test
    void test() {
        var t1 = new Thread(() -> {
            var count = 0;
            // yield 使线程由 Running 状态变为 Runnable
            while(true) {
                Thread.yield();
                log.debug("{} --- {}",Thread.currentThread().getName(),count++);
            }

        },"t1");
        t1.start();

        var t2 = new Thread(() -> {
            var count = 0;
            while(true) {
//                Thread.yield();
                log.debug("           {} --- {}",Thread.currentThread().getName(),count++);
            }

        },"t2");
        t2.start();


    }
}
