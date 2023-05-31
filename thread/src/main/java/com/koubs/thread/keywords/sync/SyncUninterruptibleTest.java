package com.koubs.thread.keywords.sync;

/**
 * 不可中断特性：一个线程获得锁后，另一个线程想要获得锁必须处于阻塞状态，如果第一个线程不释放锁，第二个线程会一直阻塞或等待，不可中断
 * 测试 synchronized 不可中断特性
 * @author KouBeisi
 * @since 2021/11/10
 */
public class SyncUninterruptibleTest {

    public static void main(String[] args) throws InterruptedException {

        Runnable run = ()->{
            synchronized (SyncUninterruptibleTest.class){
                System.out.println(Thread.currentThread().getName() + " 进入同步代码块");
                try {
                    Thread.sleep(200000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        };

        final Thread thread1 = new Thread(run);
        thread1.start();
        final Thread thread2 = new Thread(run);
        thread2.start();
        // 中断线程2
        thread2.interrupt();

        Thread.sleep(1000);
        // 获取线程的状态
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

    }
}
