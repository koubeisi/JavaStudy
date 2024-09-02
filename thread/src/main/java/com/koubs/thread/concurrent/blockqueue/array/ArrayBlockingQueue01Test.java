package com.koubs.thread.concurrent.blockqueue.array;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * <a href="https://benjaminwhx.com/2018/05/07/%E3%80%90%E7%BB%86%E8%B0%88Java%E5%B9%B6%E5%8F%91%E3%80%91%E8%B0%88%E8%B0%88ArrayBlockingQueue/">谈谈ArrayBlockingQueue</a>
 *
 * @author KouBeisi
 * @since 2021/5/16
 */
class ArrayBlockingQueue01Test {

    public static final Logger log = LoggerFactory.getLogger(ArrayBlockingQueue01Test.class);

    private final ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(8);

    /**
     * 添加对象到队列，如果满，则抛出异常
     */
    @Test
    void add() {

        final var count = 8;
        for (var i = 0; i < count; i++) {
            blockingQueue.add(i);
        }
        log.info(blockingQueue.toString());
        // 队列已满，再次添加将抛出异常
        blockingQueue.add(count);
    }

    /**
     * 添加对象到队列，如果满，则返回 false
     */
    @Test
    void offer(){
        final var count = 8;
        for (var i = 0; i < count; i++) {
            final boolean offer = blockingQueue.offer(i);
            System.out.print(offer + " ");
        }
        System.out.println();

        final boolean offer = blockingQueue.offer(8);
        System.out.println(offer);
    }

    /**
     * 添加对象到队列，如果队列满了，则 阻塞
     */
    @Test
    void put() throws InterruptedException {

        final var count = 8;
        for (var i = 0; i < count; i++) {
            blockingQueue.put(i);
            System.out.print(blockingQueue.size() + " ");
        }
        log.info(blockingQueue.toString());

        blockingQueue.put(count);
        // 程序在上一步被阻塞，因此下面的程序不会再运行
        System.out.println("never arrive");
    }
}
