package com.koubs.thread.thread._2_state;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程的六种状态
 *
 * @author koubs
 * @date 2020/10/22
 */
@Slf4j
public class ThreadStateDemo {

    private static final Object LOCK0 = new Object();
    private static final Object LOCK1 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
        }, "t3");
        t3.start();

        Thread t4 = new Thread(() -> {
            synchronized (LOCK0){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }, "t4");
        t4.start();


        Thread t5 = new Thread(() -> {
            synchronized (LOCK0) {
            }
        }, "t5");
        t5.start();


        Thread t6 = new Thread(() -> {
            while (true) {
                synchronized (LOCK1) {
                    try {
                        LOCK1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }, "t6");
        t6.start();

        log.debug("t1 " + t1.getState());
        log.debug("t2 " + t2.getState());
        log.debug("t3 " + t3.getState());
        log.debug("t4 " + t4.getState());
        log.debug("t5 " + t5.getState());
        log.debug("t6 " + t6.getState());
    }
}
