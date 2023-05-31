package org.example;

import org.junit.jupiter.api.Test;

/**
 * @author koubeisi
 * @date  2019年4月7日下午5:06:14
 * @version v1.0
 */
class IntegerTest {

    /**
     * 如果整型字面量的值在-128到127之间，那么不会new新的Integer对象，而是直接引用常量池中的Integer对象。
     */
    @Test
    void test() {
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        Integer e = -128;
        Integer f = -128;
        // true
        System.out.println(a == b);
        // false
        System.out.println(c == d);
        // true
        System.out.println(e == f);
    }

}
