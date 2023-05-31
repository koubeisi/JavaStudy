package com.koubs.thread.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程等待唤醒机制（wait/notify)加强版
 * <p>
 * 唤醒等待机制有：
 * <p>
 * 1）使用 Object 中的 wait 方法让线程等待，使用 Object 中的 notify 方法唤醒线程 <br>
 * - 必须在 synchronized 同步代码块中 <br>
 * - notify 在 wait 后面才能生效
 * <p>
 * 2）使用 JUC 包中的 Condition 的 await 方法让线程等待，使用 signal 方法唤醒线程 <br>
 * - 必须在 lock 同步代码块中 <br>
 * - signal 在 await 后面才能生效
 * <p>
 * 3）LockSupport 类的 park 和 unpark 可以阻塞当前线程以及唤醒指定被阻塞的线程 <br>
 * - unpark 和 park 之间不需要顺序
 * <p>
 * 为什么可以先唤醒线程后阻塞线程？<br>
 * 因为 unpark 获得了一个凭证，之后再调用 park 方法，就可以名正言顺的凭证消费，故不会阻塞。
 * <p>
 * 为什么唤醒两次后阻塞两次，但最终结果还会阻塞线程？<br>
 * 因为凭证的熟练最多是 1，连续调用两次 unpark 和调用溢出 unpark 效果一样，只会增加一个凭证；
 * 而调用两次 park却需要消费两个凭证，证不够，不能放行
 *
 * @author KouBeisi
 * @since 2021/11/24
 */
public class LockSupportTest {


    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "进入线程");
            // 被阻塞，等待唤醒
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "醒了");
        }, "A");
        threadA.start();

        TimeUnit.SECONDS.sleep(1);

        Thread threadB = new Thread(() -> {
            // 等待
            System.out.println(Thread.currentThread().getName() + "唤醒" + threadA.getName());
            LockSupport.unpark(threadA);
        }, "B");
        threadB.start();
    }

}
