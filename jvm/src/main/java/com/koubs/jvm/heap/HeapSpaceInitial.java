package com.koubs.jvm.heap;

/**
 * JVM 参数：
 * -Xms1024M
 * -Xmx1024M
 *
 * 默认情况下：
 * - 初始内存大小：物理内存 / 64
 * - 最大内存大小：物理内存 / 4
 *
 * @author KouBeisi
 * @since 2021/10/19
 */
public class HeapSpaceInitial {

    public static void main(String[] args) {
        final long initialMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;

        final long maxMemory = Runtime.getRuntime().maxMemory()/ 1024 /1024;

        System.out.println("-Xms:" + initialMemory + "M");
        System.out.println("-Xms:" + maxMemory + "M");

        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
