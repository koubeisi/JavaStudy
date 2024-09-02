package com.koubs.thread.concurrent.locks;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author PC
 * @since 2024/9/2
 */
public class LockSupportTest {

    /**
     * unpark after park
     */
    @Test
    void test1() {
        var t1= new Thread(() -> {
            System.out.println("park");
            LockSupport.park();
            System.out.println("unpark");
        });
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.println("main");
        LockSupport.unpark(t1);
    }

    /**
     * unpark before park
     */
    @Test
    void test2() throws InterruptedException {
        var t1= new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println("park");
            LockSupport.park();
            System.out.println("unpark");
        });
        t1.start();

        LockSupport.unpark(t1);

        t1.join();
    }
}
