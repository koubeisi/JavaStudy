package com.koubs.jvm.heap;

/**
 * -Xms600M -Xmx600M
 *
 * -XX:newRatio=ratio
 * 设置新生代和老年代的比例，默认为2
 *
 * -XX:SurvivorRatio=ratio
 * 设置新生代中 Eden 区与 Survivor 区的比例，默认值为8
 *
 * @author KouBeisi
 * @since 2021/10/19
 */
public class EdenSurvivorTest {

    public static void main(String[] args) {
        try {
            Thread.sleep(2000000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
