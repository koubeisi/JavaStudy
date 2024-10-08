package com.koubs.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

/**
 * @author PC
 * @since 2024/10/8
 */
public class QueueTest {


    @Test
    public void test() {
        var queue = new ArrayDeque<Integer>(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println(queue.poll());
        queue.offer(6);
        queue.offer(7);
        queue.offer(7);

    }

}
