package com.koubs.jvm.reference;

/**
 * -Xmx20m -Xlog:gc*
 *
 * @author KouBeisi
 * @since 2024/10/22
 */
public class SoftReference {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {


        var sr = new java.lang.ref.SoftReference<>(new byte[_1MB * 10]);

        System.out.println(sr.get());

        var b = new byte[_1MB * 12];
        System.out.println(sr.get());
        System.out.println(sr);
    }
}
