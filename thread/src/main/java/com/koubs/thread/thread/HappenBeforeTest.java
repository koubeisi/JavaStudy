package com.koubs.thread.thread;

import org.junit.jupiter.api.Test;

/**
 * @since 2025/2/14
 */
public class HappenBeforeTest {

    private static int number = 0;

    /**
     *  Thread.start() 方法 Happens-Before 该线程的每一个动作，意味着：<br/>
     *  当一个线程 t 调用 t.start() 方法后，t 线程内的所有操作都能看到 start() 之前的内存状态，即 start() 之前的修改对 t 线程是可见的。
     */
    @Test
    void threadTest() {
        Thread t = new Thread(() -> System.out.println("Thread started. number = " + number));

        number = 42; // 主线程修改变量
        t.start(); // Happens-Before 规则保证 number = 42 对新线程可见
    }

    /**
     * 「对一个锁的解锁（unlock），Happens-Before 在随后对同一个锁的加锁（lock）」<br/>
     * 意味着：<br/>
     * 一个线程对某个锁 unlock 之前的所有操作，对随后获取该锁的线程是可见的。<br/>
     * 在 unlock 之后，另一个线程 lock 该锁时，一定可以看到之前线程修改的最新数据。
     */
    @Test
    void synchronizedTest() throws InterruptedException {
        SharedResource resource = new SharedResource();
        Thread t1 = new Thread(resource::write);
        Thread t2 = new Thread(resource::read);

        t1.start();
        t1.join(); // 确保 t1 执行完再执行 t2

        t2.start();
        t2.join();
    }
}
class SharedResource {
    private int count = 0;

    public synchronized void write() { // 线程 A 执行
        count = 42;
        System.out.println("Thread A 写入: " + count);
    }

    public synchronized void read() { // 线程 B 执行
        System.out.println("Thread B 读取: " + count);
    }
}