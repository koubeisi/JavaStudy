package com.koubs.thread.keywords.sync.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁
 * <p>
 * -XX:UseBiasedLocking=false 可以关闭偏向锁
 *
 * @author KouBeisi
 * @since 2021/11/16
 */
public class BiasLockTest {

    static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            synchronized (obj) {
                System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            }
        }).start();

    }
}

