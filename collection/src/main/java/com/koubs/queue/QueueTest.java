package com.koubs.queue;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 * <p>1. <code>DelayQueue</code> 的工作原理</br>
 * <code>DelayQueue</code> 是一个基于 优先队列（<code>PriorityQueue</code>）的无界阻塞队列，按任务的延迟时间排序，延迟时间最短的任务在队列前面。
 * 只有到期的任务才能从队列中取出，任务的延迟时间通过 Delayed 接口的 getDelay() 方法来定义。
 * 队列头部的任务一旦到期，可以通过 <code>poll()</code>、<code>take()</code> 方法取出，前者不会阻塞，后者会阻塞直到有任务到期。</p>
 * <p>2. <code>take()</code> 方法的详解</br>
 * <code>take()</code> 方法使用了 leader-follower 模式，其中一个线程（leader）负责等待队列头部任务到期，其他线程（follower）则等待 leader 线程的结果。
 * 只有一个线程会成为 leader，其他线程通过 <code>Condition.await()</code> 进入等待状态，直到 leader 线程释放锁。
 * 这种机制减少了多个线程同时竞争锁和计算等待时间的开销，优化了多线程访问的性能。</p>
 * <p>3. <code>DelayQueue</code> 的时间精准性问题</br>
 * 如果有大量相同延迟时间的任务，<code>DelayQueue</code> 由于其轮询机制，不能同时触发所有任务。
 * 当多个任务有相同的延迟时间，任务处理仍然是一个接一个进行的，导致第一个任务和最后一个任务的触发时间可能相差较大。
 * 这个问题在任务数量较大时尤为明显，因为队列中的任务需要按顺序处理，每个任务的触发时间依赖于前一个任务的处理完成。</p>
 *
 * @author PC
 * @since 2024/10/8
 */
public class QueueTest {

    /**
     * 测试栈
     */
    @Test
    public void stackTest() {
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

    /**
     * 测试 DelayQueue
     */
    @Test
    public void delayQueueTest() throws InterruptedException {

        class DelayTask implements Delayed {

            @Getter
            private final String taskName;
            private final long expireTime; // 任务的到期时间

            public DelayTask(String taskName, long delay, TimeUnit unit) {
                this.taskName = taskName;
                this.expireTime = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(delay, unit);
            }

            @Override
            public long getDelay(TimeUnit unit) {
                long remainingTime = expireTime - System.currentTimeMillis();
                return unit.convert(remainingTime, TimeUnit.MILLISECONDS);
            }

            @Override
            public int compareTo(Delayed o) {
                return Long.compare(this.expireTime, ((DelayTask) o).expireTime);
            }
        }

        var delayQueue = new DelayQueue<DelayTask>();
        delayQueue.put(new DelayTask("Task1", 5, TimeUnit.SECONDS));
        delayQueue.put(new DelayTask("Task2", 3, TimeUnit.SECONDS));
        delayQueue.put(new DelayTask("Task3", 10, TimeUnit.SECONDS));

        while (!delayQueue.isEmpty()) {
            var element = delayQueue.take(); // 阻塞直到有元素到期
            System.out.println("Processing: " + element.getTaskName());
        }
    }
}
