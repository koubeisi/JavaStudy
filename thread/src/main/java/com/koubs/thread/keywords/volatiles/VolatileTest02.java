package com.koubs.thread.keywords.volatiles;

/**
 * 测试 volatile 关键字的原子性
 *
 * volatile 并不能保证原子性
 *
 * @author KouBeisi
 * @since 2021/9/14
 */
public class VolatileTest02 {

    private int i = 0;

    public void increase() {
        i++;
    }

    public int getI() {
        return i;
    }

    public static void main(String[] args) {
        final VolatileTest02 test02 = new VolatileTest02();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test02.increase();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(test02.getI());
    }
}
