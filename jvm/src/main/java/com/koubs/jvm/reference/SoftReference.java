package com.koubs.jvm.reference;

import java.util.List;

/**
 * -Xmx10m -Xlog:gc*
 * @author KouBeisi
 * @since 2024/10/22
 */
public class SoftReference {

    private static final int _1MB = 1 * 1024 * 1024;

    public static void main(String[] args) {

        List<java.lang.ref.SoftReference<byte[]>> list = new java.util.LinkedList<>();
        for (int i = 0; i <10; i++) {
            var softReference = new java.lang.ref.SoftReference<>(new byte[_1MB/2]);
            list.add(softReference);
            System.out.println(i);
        }

        for (var softReference : list){
            System.out.println(softReference.get());
        }
    }
}
