package com.koubs.jvm.reference;

import java.util.List;

/**
 * -Xmx10m -Xlog:gc*
 * @author KouBeisi
 * @since 2024/10/22
 */
public class StrongReference {

    private static final int _1MB = 1 * 1024 * 1024;

    public static void main(String[] args) {
        List<byte[]> list = new java.util.LinkedList<>();
        while (true) {
            list.add(new byte[_1MB]);
        }
    }
}
