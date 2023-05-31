package com.koubs.thread.concurrent.blockqueue.synchronous;

import org.junit.jupiter.api.Test;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue 是一个由链表或栈结构组成的阻塞队列，采用无锁算法，并发安全。
 * 每个线程的存入操作必须等待一个取出操作与之匹配（反之亦然），否则当前线程将阻塞在队列中等待匹配。
 *
 * 如果是公平模式就使用队列，如果是非公平模式就使用栈，默认使用栈（非公平）
 * @author KouBeisi
 * @since 2021/8/23
 */
public class SynchronousQueueTest {

    private final SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

    @Test
    void test() throws InterruptedException {

        handler(synchronousQueue);

        for (int i = 0; i < 8; i++) {
            synchronousQueue.put(i);
            System.out.println("已放入队列：" + i);
        }

        Thread.sleep(10000);
    }

    void handler(SynchronousQueue<Integer> queue) throws InterruptedException {
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                try {
                    final Integer take = queue.take();
                    System.out.println("取出：" + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }).start();

            Thread.sleep(500);
        }
    }
}
