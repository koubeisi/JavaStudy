package com.koubs.thread.concurrent.blockqueue.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * <a href="https://benjaminwhx.com/2018/05/07/%E3%80%90%E7%BB%86%E8%B0%88Java%E5%B9%B6%E5%8F%91%E3%80%91%E8%B0%88%E8%B0%88ArrayBlockingQueue/">谈谈ArrayBlockingQueue</a>
 *
 * @author KouBeisi
 * @since 2021/5/16
 */
@Slf4j
class ArrayBlockingQueue02Test {

    private static final ArrayBlockingQueue<Integer> BLOCKING_QUEUE = new ArrayBlockingQueue<>(8);
    private static final int COUNT = 8;

    @BeforeAll
    static void beforeAll() {
        for (var i = 0; i < COUNT; i++) {
            BLOCKING_QUEUE.add(i);
        }
        System.out.println(BLOCKING_QUEUE);
    }

    /**
     * poll方法是非阻塞方法
     * 如果队列没有元素返回 null，否则调用dequeue把队首的元素出队列
     */
    @Test
    void poll() {
        int num = COUNT + 2;
        for (int i = 0; i < num; i++) {
            final Integer integer = BLOCKING_QUEUE.poll();
            System.out.print(integer + " ");
        }
    }

    /**
     * take 方法是阻塞方法
     * 如果队列中没有元素，则一直等待阻塞
     */
    @Test
    void take() throws InterruptedException {
        int num = COUNT + 1;
        for (int i = 0; i < num; i++) {
            final Integer integer = BLOCKING_QUEUE.take();
            System.out.println(integer);
        }
    }

    /**
     * poll方法是非阻塞方法
     * 如果队列没有元素则抛出异常
     */
    @Test
    void remove() {
        int num = COUNT + 1;
        for (int i = 0; i < num; i++) {
            final Integer integer = BLOCKING_QUEUE.remove();
            System.out.print(integer + " ");
        }

    }
}
